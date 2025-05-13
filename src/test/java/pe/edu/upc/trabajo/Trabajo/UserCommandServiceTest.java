package pe.edu.upc.trabajo.Trabajo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upc.trabajo.Trabajo.user.application.internal.commandServices.UserCommandService;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.IUserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserCommandServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserCommandService userCommandService;

    @Test
    void shouldRegisterNewUser() {
        // Arrange
        RegisterUserCommand command = new RegisterUserCommand("Nuevo", "Usuario", "nuevo@example.com", "newpass");
        User newUser = new User(command);
        when(userRepository.findByEmailAndPassword(command.email(), command.password())).thenReturn(null); // Simulate user not existing
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        // Act
        Optional<User> result = userCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(command.name(), result.get().getName());
        assertEquals(command.email(), result.get().getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldNotRegisterExistingUser() {
        // Arrange
        RegisterUserCommand command = new RegisterUserCommand("Existing", "User", "existing@example.com", "existpass");
        User existingUser = new User(1L, command.name(), command.lastName(), command.email(), command.password());
        when(userRepository.findByEmailAndPassword(command.email(), command.password())).thenReturn(existingUser);

        // Act
        Optional<User> result = userCommandService.handle(command);

        // Assert
        assertFalse(result.isPresent());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldUpdateExistingUser() {
        // Arrange
        Long userId = 1L;
        UpdateUserCommand command = new UpdateUserCommand(userId, "Updated", "User", "updated@example.com", "updatedpass");
        User existingUser = new User(userId, "Old", "User", "old@example.com", "oldpass");
        User updatedUser = new User(userId, command.name(), command.lastName(), command.email(), command.password());
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Act
        Optional<User> result = userCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(command.name(), result.get().getName());
        assertEquals(command.email(), result.get().getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldNotUpdateNonExistingUser() {
        // Arrange
        Long userId = 99L;
        UpdateUserCommand command = new UpdateUserCommand(userId, "Updated", "User", "updated@example.com", "updatedpass");
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userCommandService.handle(command);

        // Assert
        assertFalse(result.isPresent());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldDeleteExistingUser() {
        // Arrange
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);
        ArgumentCaptor<Long> userIdCaptor = ArgumentCaptor.forClass(Long.class);

        // Act
        userCommandService.handle(new DeleteUserCommand(userId));

        // Assert
        verify(userRepository, times(1)).deleteById(userIdCaptor.capture());
        assertEquals(userId, userIdCaptor.getValue());
    }

    @Test
    void shouldNotDeleteNonExistingUser() {
        // Arrange
        Long userId = 99L;
        when(userRepository.existsById(userId)).thenReturn(false);

        // Act
        userCommandService.handle(new DeleteUserCommand(userId));

        // Assert
        verify(userRepository, never()).deleteById(anyLong());
    }
}
