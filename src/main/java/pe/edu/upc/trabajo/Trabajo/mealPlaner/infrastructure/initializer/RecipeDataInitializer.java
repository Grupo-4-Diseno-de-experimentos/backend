package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;

import java.util.List;

@Component
public class RecipeDataInitializer implements CommandLineRunner {

    private final IRecipeRepository recipeRepository;

    public RecipeDataInitializer(IRecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) {
        addIfNotExists("Ensalada de quinua y garbanzos", "Alta en proteínas y fibra",
                "Cocina la quinua, mezcla con garbanzos cocidos, pepino, tomate y limón", 350L);

        addIfNotExists("Salmón al horno con espárragos", "Fuente de omega-3 y vitaminas",
                "Hornear el salmón con espárragos, aceite de oliva y especias", 420L);

        addIfNotExists("Bowl de avena con frutas", "Ideal para el desayuno",
                "Cocinar la avena en agua o leche, añadir plátano, fresas y chía", 300L);

        addIfNotExists("Pechuga de pollo con arroz y verduras", "Plato equilibrado de proteínas y carbohidratos ideal para después del entrenamiento",
                "Cocer pollo, preparar arroz y añadir verduras al vapor", 450L);

        addIfNotExists("Ensalada de lentejas", "Ensalada rica en proteína vegetal y fibra",
                "Cocer lentejas, mezclar con vegetales frescos y aderezo de limón", 310L);

        addIfNotExists("Bowl de salmón con aguacate", "Plato rico en grasas saludables y proteínas de alta calidad",
                "Servir salmón cocido con aguacate, arroz integral y vegetales", 520L);

        addIfNotExists("Batido verde proteico", "Batido nutritivo ideal para después del entrenamiento",
                "Licuar espinaca, proteína en polvo, plátano y leche vegetal", 281L);

        addIfNotExists("Tofu salteado con verduras", "Alternativa vegetariana rica en proteínas",
                "Saltear tofu en cubos con brócoli, zanahoria y salsa de soya", 350L);

        addIfNotExists("Crema de lentejas", "Plato reconfortante rico en fibra y proteínas",
                "Hervir lentejas con zanahoria, cebolla y licuar hasta obtener una crema", 290L);

        addIfNotExists("Tortilla de espinacas", "Desayuno o cena rica en proteínas y nutrientes",
                "Batir huevos con espinacas salteadas y cocinar como tortilla", 320L);

        addIfNotExists("Tortilla de espinaca y claras", "Baja en calorías y rica en hierro",
                "Saltear espinacas, añadir claras de huevo y cocinar como tortilla", 250L);

        addIfNotExists("Sopa de lentejas", "Rica en hierro y fibra",
                "Cocinar lentejas con verduras, especias y un poco de tomate", 330L);

        addIfNotExists("Sopa de verduras", "Rica en hierro y fibra",
                "Cocinar lentejas con verduras, especias y un poco de tomate", 330L);
    }

    private void addIfNotExists(String title, String description, String instructions, Long calories) {
        if (recipeRepository.findByTitle(title).isEmpty()) {
            recipeRepository.save(new Recipe(null, title, description, instructions, calories));
            System.out.println("Receta insertada: " + title);
        }
    }
}
