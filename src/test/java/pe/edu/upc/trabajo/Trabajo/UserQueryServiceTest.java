package pe.edu.upc.trabajo.Trabajo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upc.trabajo.Trabajo.user.application.internal.queryServices.UserQueryService;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetUserByEmailAndPasswordQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.IUserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserQueryService userQueryService;

    @Test
    void shouldReturnUserWhenIdExists() {
        // Arrange
        Long userId = 1L;
        User expectedUser = new User(userId, "Juan", "Perez", "juan.perez@example.com", "password123");
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        // Act
        Optional<User> result = userQueryService.handle(new GetUserByIdQuery(userId));

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedUser.getId(), result.get().getId());
        assertEquals(expectedUser.getName(), result.get().getName());
        assertEquals(expectedUser.getEmail(), result.get().getEmail());
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        // Arrange
        Long userId = 99L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userQueryService.handle(new GetUserByIdQuery(userId));

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnUserWhenEmailAndPasswordMatch() {
        // Arrange
        String email = "juan.perez@example.com";
        String password = "password123";
        User expectedUser = new User(1L, "Juan", "Perez", email, password);
        when(userRepository.searchByEmailAndPassword(email, password)).thenReturn(Optional.of(expectedUser));

        // Act
        User result = userQueryService.handle(new GetUserByEmailAndPasswordQuery(email, password));

        // Assert
        assertNotNull(result);
        assertEquals(expectedUser.getId(), result.getId());
        assertEquals(expectedUser.getName(), result.getName());
        assertEquals(expectedUser.getEmail(), result.getEmail());
    }

    @Test
    void shouldReturnNullWhenEmailAndPasswordDoNotMatch() {
        // Arrange
        String email = "wrong@example.com";
        String password = "wrongpassword";
        when(userRepository.searchByEmailAndPassword(email, password)).thenReturn(Optional.empty());

        // Act
        User result = userQueryService.handle(new GetUserByEmailAndPasswordQuery(email, password));

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnAllUsers() {
        // Arrange
        List<User> expectedUsers = Arrays.asList(
                new User(1L, "Juan", "Perez", "juan.perez@example.com", "pass1"),
                new User(2L, "Maria", "Lopez", "maria.lopez@example.com", "pass2")
        );
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<User> result = userQueryService.handle(new GetAllUsersQuery());

        // Assert
        assertEquals(expectedUsers.size(), result.size());
        assertEquals(expectedUsers.get(0).getName(), result.get(0).getName());
        assertEquals(expectedUsers.get(1).getEmail(), result.get(1).getEmail());
    }
}
