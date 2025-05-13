package pe.edu.upc.trabajo.Trabajo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upc.trabajo.Trabajo.user.application.internal.commandServices.NutricionistCommandService;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.DeleteNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.UpdateNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.INutricionistRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NutricionistCommandServiceTest {

    @Mock
    private INutricionistRepository nutricionistRepository;

    @InjectMocks
    private NutricionistCommandService nutricionistCommandService;

    @Test
    void shouldRegisterNewNutricionist() {
        // Arrange
        RegisterNutricionistCommand command = new RegisterNutricionistCommand("Laura", "Gutierrez", "LGU456");
        Nutricionist newNutricionist = new Nutricionist(command);
        when(nutricionistRepository.findByCode(command.code())).thenReturn(Optional.empty()); // Simulate code not existing
        when(nutricionistRepository.save(any(Nutricionist.class))).thenReturn(newNutricionist);

        // Act
        Optional<Nutricionist> result = nutricionistCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(command.name(), result.get().getName());
        assertEquals(command.code(), result.get().getCode());
        verify(nutricionistRepository, times(1)).save(any(Nutricionist.class));
    }

    @Test
    void shouldNotRegisterExistingNutricionist() {
        // Arrange
        RegisterNutricionistCommand command = new RegisterNutricionistCommand("Existing", "Nutri", "EXI789");
        Nutricionist existingNutricionist = new Nutricionist(1L, command.name(), command.lastName(), command.code());
        when(nutricionistRepository.findByCode(command.code())).thenReturn(Optional.of(existingNutricionist));

        // Act
        Optional<Nutricionist> result = nutricionistCommandService.handle(command);

        // Assert
        assertFalse(result.isPresent());
        verify(nutricionistRepository, never()).save(any(Nutricionist.class));
    }

    @Test
    void shouldUpdateExistingNutricionist() {
        // Arrange
        Long nutricionistId = 1L;
        UpdateNutricionistCommand command = new UpdateNutricionistCommand(nutricionistId, "Updated", "Nutri", "UPD123");
        Nutricionist existingNutricionist = new Nutricionist(nutricionistId, "Old", "Nutri", "OLD456");
        Nutricionist updatedNutricionist = new Nutricionist(nutricionistId, command.name(), command.lastName(), command.code());
        when(nutricionistRepository.findById(nutricionistId)).thenReturn(Optional.of(existingNutricionist));
        when(nutricionistRepository.save(any(Nutricionist.class))).thenReturn(updatedNutricionist);

        // Act
        Optional<Nutricionist> result = nutricionistCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(command.name(), result.get().getName());
        assertEquals(command.code(), result.get().getCode());
        verify(nutricionistRepository, times(1)).save(any(Nutricionist.class));
    }

    @Test
    void shouldNotUpdateNonExistingNutricionist() {
        // Arrange
        Long nutricionistId = 99L;
        UpdateNutricionistCommand command = new UpdateNutricionistCommand(nutricionistId, "Updated", "Nutri", "UPD123");
        when(nutricionistRepository.findById(nutricionistId)).thenReturn(Optional.empty());

        // Act
        Optional<Nutricionist> result = nutricionistCommandService.handle(command);

        // Assert
        assertFalse(result.isPresent());
        verify(nutricionistRepository, never()).save(any(Nutricionist.class));
    }

    @Test
    void shouldDeleteExistingNutricionist() {
        // Arrange
        Long nutricionistId = 1L;
        when(nutricionistRepository.existsById(nutricionistId)).thenReturn(true);
        ArgumentCaptor<Long> nutricionistIdCaptor = ArgumentCaptor.forClass(Long.class);

        // Act
        nutricionistCommandService.handle(new DeleteNutricionistCommand(nutricionistId));

        // Assert
        verify(nutricionistRepository, times(1)).deleteById(nutricionistIdCaptor.capture());
        assertEquals(nutricionistId, nutricionistIdCaptor.getValue());
    }

    @Test
    void shouldNotDeleteNonExistingNutricionist() {
        // Arrange
        Long nutricionistId = 99L;
        when(nutricionistRepository.existsById(nutricionistId)).thenReturn(false);

        // Act
        nutricionistCommandService.handle(new DeleteNutricionistCommand(nutricionistId));

        // Assert
        verify(nutricionistRepository, never()).deleteById(anyLong());
    }
}
