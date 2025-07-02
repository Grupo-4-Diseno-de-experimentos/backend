package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.RatingService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Rating;

@RestController
@RequestMapping("/test/Rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping("/rate")
    public ResponseEntity<Rating> rateMealPlan(@RequestParam Long mealPlanId,
                                               @RequestParam Long userId,
                                               @RequestParam int rating) {
        Rating savedRating = ratingService.rateMealPlan(mealPlanId, userId, rating);
        return ResponseEntity.ok(savedRating);
    }

    @GetMapping("/average")
    public ResponseEntity<Double> getAverageRating(@RequestParam Long mealPlanId) {
        double average = ratingService.getAverageRating(mealPlanId);
        return ResponseEntity.ok(average);
    }
}
