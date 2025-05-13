package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Macros;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IIngredientRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeDataInitializer implements CommandLineRunner {

    private final IRecipeRepository recipeRepository;
    private final IIngredientRepository ingredientRepository;

    public RecipeDataInitializer(IRecipeRepository recipeRepository, IIngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) {
        addIfNotExists("Ensalada de quinua y garbanzos", "Alta en proteínas y fibra",
                "Cocina la quinua, mezcla con garbanzos cocidos, pepino, tomate y limón", 350L, 40.0, 15.0, 10.0,List.of(1L,2L));

        addIfNotExists("Salmón al horno con espárragos", "Fuente de omega-3 y vitaminas",
                "Hornear el salmón con espárragos, aceite de oliva y especias", 420L, 5.0, 35.0, 20.0,List.of(2L,7L));

        addIfNotExists("Bowl de avena con frutas", "Ideal para el desayuno",
                "Cocinar la avena en agua o leche, añadir plátano, fresas y chía", 300L, 45.0, 10.0, 6.0,List.of(3L,4L));

        addIfNotExists("Pechuga de pollo con arroz y verduras", "Plato equilibrado de proteínas y carbohidratos ideal para después del entrenamiento",
                "Cocer pollo, preparar arroz y añadir verduras al vapor", 450L, 35.0, 40.0, 8.0,List.of(6L,2L));

        addIfNotExists("Ensalada de lentejas", "Ensalada rica en proteína vegetal y fibra",
                "Cocer lentejas, mezclar con vegetales frescos y aderezo de limón", 310L, 30.0, 18.0, 5.0,List.of(5L,4L));

        addIfNotExists("Bowl de salmón con aguacate", "Plato rico en grasas saludables y proteínas de alta calidad",
                "Servir salmón cocido con aguacate, arroz integral y vegetales", 520L, 25.0, 35.0, 25.0,List.of(2L,6L));

        addIfNotExists("Batido verde proteico", "Batido nutritivo ideal para después del entrenamiento",
                "Licuar espinaca, proteína en polvo, plátano y leche vegetal", 281L, 20.0, 20.0, 6.0,List.of(1L,2L));

        addIfNotExists("Tofu salteado con verduras", "Alternativa vegetariana rica en proteínas",
                "Saltear tofu en cubos con brócoli, zanahoria y salsa de soya", 350L, 15.0, 20.0, 18.0,List.of(1L,2L));

        addIfNotExists("Crema de lentejas", "Plato reconfortante rico en fibra y proteínas",
                "Hervir lentejas con zanahoria, cebolla y licuar hasta obtener una crema", 290L, 30.0, 16.0, 4.0,List.of(2L,4L));

        addIfNotExists("Tortilla de espinacas", "Desayuno o cena rica en proteínas y nutrientes",
                "Batir huevos con espinacas salteadas y cocinar como tortilla", 320L, 5.0, 20.0, 22.0,List.of(1L,2L));

        addIfNotExists("Tortilla de espinaca y claras", "Baja en calorías y rica en hierro",
                "Saltear espinacas, añadir claras de huevo y cocinar como tortilla", 250L, 4.0, 18.0, 10.0,List.of(1L,2L));

        addIfNotExists("Sopa de lentejas", "Rica en hierro y fibra",
                "Cocinar lentejas con verduras, especias y un poco de tomate", 330L, 30.0, 17.0, 6.0,List.of(1L,2L));

        addIfNotExists("Sopa de verduras", "Rica en hierro y fibra",
                "Cocinar lentejas con verduras, especias y un poco de tomate", 330L, 25.0, 12.0, 5.0,List.of(1L,2L));
    }

    private void addIfNotExists(String title, String description, String instructions, Long calories, Double carbs, Double protein, Double fats, List<Long> ingredientIds) {
        if (recipeRepository.findByTitle(title).isEmpty()) {
            List<Ingredient> ingredients = ingredientIds.stream()
                    .map(id -> ingredientRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado: " + id)))
                    .collect(Collectors.toList());

            Macros macros = new Macros(carbs, protein, fats);

            Recipe recipe = new Recipe(null, title, description, instructions, calories, macros, ingredients);

            recipeRepository.save(recipe);
            System.out.println("Receta insertada: " + title);
        }
    }
}
