package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.commandServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.DeleteMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services.IMealPlansCommandService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IMealPlanRepository;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.INutricionistRepository;

import java.util.Optional;

@Service
public class MealPlanCommandService implements IMealPlansCommandService {
    private final IMealPlanRepository mealPlanRepository;
    private final INutricionistRepository nutricionistRepository;

    public MealPlanCommandService(IMealPlanRepository mealPlanRepository, INutricionistRepository nutricionistRepository) {
        this.mealPlanRepository = mealPlanRepository;
        this.nutricionistRepository = nutricionistRepository;
    }


    @Override
    public Optional<MealPlan> handle(CreateMealPlanCommand command) {
        try {
            MealPlan mealPlan = new MealPlan(command);
            MealPlan saved = mealPlanRepository.save(mealPlan);
            return Optional.of(saved);

        } catch (Exception e) {
            System.out.println("Error creating meal plan: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<MealPlan> handle(UpdateMealPlanCommand command) {
        try {
            Optional<MealPlan> existing = mealPlanRepository.findById(command.mealPlanId());
            if (existing.isEmpty()) return Optional.empty();

            MealPlan mealPlan = existing.get();
            mealPlan.updateMealPlanCommand(command);

            MealPlan updated = mealPlanRepository.save(mealPlan);
            return Optional.of(updated);

        } catch (Exception e) {
            System.out.println("Error updating meal plan: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void handle(DeleteMealPlanCommand command) {
        try {
            if (mealPlanRepository.existsById(command.mealPlanId())) {
                mealPlanRepository.deleteById(command.mealPlanId());
            }
        } catch (Exception e) {
            System.out.println("Error deleting meal plan: " + e.getMessage());
        }
    }
}
