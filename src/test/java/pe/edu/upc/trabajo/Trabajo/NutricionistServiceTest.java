package pe.edu.upc.trabajo.Trabajo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upc.trabajo.Trabajo.user.application.internal.queryServices.NutricionistQueryService;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.INutricionistRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NutricionistServiceTest {
    @Mock
    private INutricionistRepository repository;

    @InjectMocks
    private NutricionistQueryService service;

    @Test
    void shouldReturnNutricionistWhenCodeIsValid() {
        // Arrange
        String code = "ABC123";
        RegisterNutricionistCommand command = new RegisterNutricionistCommand("Carlos", "Apellido", code);
        Nutricionist expected = new Nutricionist(command);
        expected.setId(1L);

        Mockito.when(repository.findByCode(code)).thenReturn(Optional.of(expected));

        // Act
        Optional<Nutricionist> result = service.login(code);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Carlos", result.get().getName());
        assertEquals(code, result.get().getCode());
    }

    @Test
    void shouldReturnEmptyWhenCodeIsInvalid() {
        String code = "INVALID";

        Mockito.when(repository.findByCode(code)).thenReturn(Optional.empty());

        Optional<Nutricionist> result = service.login(code);

        assertFalse(result.isPresent());
    }
}
