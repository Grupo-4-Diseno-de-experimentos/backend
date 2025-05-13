package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Macros;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IIngredientRepository;

@Component
public class IngredientDataLoader implements CommandLineRunner {

    private final IIngredientRepository ingredientRepository;

    public IngredientDataLoader(IIngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) {
        addIfNotExists("Pollo", 100f, 165f, 0f, 31f, 3.6f, "proteína animal", true);
        addIfNotExists("Arroz blanco", 100f, 130f, 28f, 2.7f, 0.3f, "carbohidrato", true);
        addIfNotExists("Aguacate", 100f, 160f, 8.5f, 2f, 14.7f, "grasa saludable", true);
        addIfNotExists("Lentejas", 100f, 116f, 20f, 9f, 0.4f, "legumbre", true);
        addIfNotExists("Salmón", 100f, 208f, 0f, 20f, 13f, "proteína animal", true);
        addIfNotExists("Espinacas", 100f, 23f, 3.6f, 2.9f, 0.4f, "verdura", true);
        addIfNotExists("Plátano", 100f, 89f, 22.8f, 1.1f, 0.3f, "fruta", true);
    }

    private void addIfNotExists(String name, Float quantity, Float calories, Float carbs, Float protein, Float fats, String category, Boolean available) {
        if (ingredientRepository.findByName(name).isEmpty()) {
            Ingredient ingredient = new Ingredient(name, quantity, calories, carbs, protein, fats, category, available);
            ingredientRepository.save(ingredient);
            System.out.println("Ingrediente insertado: " + name);
        }
    }
}
