package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record AssignMealPlanToCustomerCommand(
        Long customerId,
        Long mealPlanId,
        LocalDate startDate,
        LocalDate endDate
) {
}
