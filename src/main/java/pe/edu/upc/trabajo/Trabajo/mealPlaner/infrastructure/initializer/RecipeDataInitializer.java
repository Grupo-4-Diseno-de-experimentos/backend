package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Macros;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.RecipeIngredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IIngredientRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeIngredientRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeDataInitializer implements CommandLineRunner {

    private final IRecipeRepository recipeRepository;
    private final IIngredientRepository ingredientRepository;
    private final IRecipeIngredientRepository recipeIngredientRepository;

    public RecipeDataInitializer(IRecipeRepository recipeRepository,
                                 IIngredientRepository ingredientRepository,
                                 IRecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @Override
    public void run(String... args) {
        addIfNotExists("Ensalada de quinua y garbanzos", "Alta en proteínas y fibra",
                "Cocina la quinua, mezcla con garbanzos cocidos, pepino, tomate y limón", 350L, 40.0, 15.0, 10.0,
                List.of(new IngredientEntry(1L, 100.0), new IngredientEntry(2L, 150.0)));

        addIfNotExists("Salmón al horno con espárragos", "Fuente de omega-3 y vitaminas",
                "Hornear el salmón con espárragos, aceite de oliva y especias", 420L, 5.0, 35.0, 20.0,
                List.of(new IngredientEntry(2L, 120.0), new IngredientEntry(7L, 100.0)));

        addIfNotExists("Bowl de avena con frutas", "Ideal para el desayuno",
                "Cocinar la avena en agua o leche, añadir plátano, fresas y chía", 300L, 45.0, 10.0, 6.0,
                List.of(new IngredientEntry(3L, 80.0), new IngredientEntry(4L, 50.0)));

        addIfNotExists("Pechuga de pollo con arroz y verduras", "Plato equilibrado de proteínas y carbohidratos ideal para después del entrenamiento",
                "Cocer pollo, preparar arroz y añadir verduras al vapor", 450L, 35.0, 40.0, 8.0,
                List.of(new IngredientEntry(6L, 150.0), new IngredientEntry(2L, 100.0)));

        addIfNotExists("Ensalada de lentejas", "Ensalada rica en proteína vegetal y fibra",
                "Cocer lentejas, mezclar con vegetales frescos y aderezo de limón", 310L, 30.0, 18.0, 5.0,
                List.of(new IngredientEntry(5L, 120.0), new IngredientEntry(4L, 50.0)));

        addIfNotExists("Bowl de salmón con aguacate", "Plato rico en grasas saludables y proteínas de alta calidad",
                "Servir salmón cocido con aguacate, arroz integral y vegetales", 520L, 25.0, 35.0, 25.0,
                List.of(new IngredientEntry(2L, 120.0), new IngredientEntry(6L, 100.0)));

        addIfNotExists("Batido verde proteico", "Batido nutritivo ideal para después del entrenamiento",
                "Licuar espinaca, proteína en polvo, plátano y leche vegetal", 281L, 20.0, 20.0, 6.0,
                List.of(new IngredientEntry(1L, 30.0), new IngredientEntry(2L, 40.0)));

        addIfNotExists("Tofu salteado con verduras", "Alternativa vegetariana rica en proteínas",
                "Saltear tofu en cubos con brócoli, zanahoria y salsa de soya", 350L, 15.0, 20.0, 18.0,
                List.of(new IngredientEntry(1L, 100.0), new IngredientEntry(2L, 80.0)));

        addIfNotExists("Crema de lentejas", "Plato reconfortante rico en fibra y proteínas",
                "Hervir lentejas con zanahoria, cebolla y licuar hasta obtener una crema", 290L, 30.0, 16.0, 4.0,
                List.of(new IngredientEntry(2L, 100.0), new IngredientEntry(4L, 50.0)));

        addIfNotExists("Tortilla de espinacas", "Desayuno o cena rica en proteínas y nutrientes",
                "Batir huevos con espinacas salteadas y cocinar como tortilla", 320L, 5.0, 20.0, 22.0,
                List.of(new IngredientEntry(1L, 70.0), new IngredientEntry(2L, 60.0)));

        addIfNotExists("Tortilla de espinaca y claras", "Baja en calorías y rica en hierro",
                "Saltear espinacas, añadir claras de huevo y cocinar como tortilla", 250L, 4.0, 18.0, 10.0,
                List.of(new IngredientEntry(1L, 80.0), new IngredientEntry(2L, 50.0)));

        addIfNotExists("Sopa de lentejas", "Rica en hierro y fibra",
                "Cocinar lentejas con verduras, especias y un poco de tomate", 330L, 30.0, 17.0, 6.0,
                List.of(new IngredientEntry(1L, 100.0), new IngredientEntry(2L, 80.0)));

        addIfNotExists("Sopa de verduras", "Rica en hierro y fibra",
                "Cocinar lentejas con verduras, especias y un poco de tomate", 330L, 25.0, 12.0, 5.0,
                List.of(new IngredientEntry(1L, 90.0), new IngredientEntry(2L, 60.0)));
    }

    private void addIfNotExists(String title, String description, String instructions, Long calories,
                                Double carbs, Double protein, Double fats, List<IngredientEntry> ingredientEntries) {

        if (recipeRepository.findByTitle(title).isEmpty()) {
            Macros macros = new Macros(carbs, protein, fats);

            Recipe recipe = new Recipe();
            recipe.setTitle(title);
            recipe.setDescription(description);
            recipe.setInstructions(instructions);
            recipe.setCalories(calories);
            recipe.setMacros(macros);

            recipe = recipeRepository.save(recipe); // Persistir primero

            for (IngredientEntry entry : ingredientEntries) {
                Ingredient ingredient = ingredientRepository.findById(entry.ingredientId())
                        .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado: " + entry.ingredientId()));

                RecipeIngredient recipeIngredient = new RecipeIngredient();
                recipeIngredient.setRecipe(recipe);
                recipeIngredient.setIngredient(ingredient);
                recipeIngredient.setQuantity(entry.quantity());

                recipeIngredientRepository.save(recipeIngredient);
            }

            System.out.println("Receta insertada: " + title);
        }
    }

    private record IngredientEntry(Long ingredientId, Double quantity) {
    }
}
