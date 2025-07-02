package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Rating;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IMealPlanRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRatingRepository;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.IUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private IRatingRepository ratingRepository;

    @Autowired
    private IMealPlanRepository mealPlanRepository;

    @Autowired
    private IUserRepository userRepository;

    public Rating rateMealPlan(Long mealPlanId, Long userId, int rating) {
        Optional<Rating> existingRating = ratingRepository.findByMealPlanIdAndUserId(mealPlanId, userId);
        if (existingRating.isPresent()) {
            Rating ratingToUpdate = existingRating.get();
            ratingToUpdate.setRating(rating);
            return ratingRepository.save(ratingToUpdate);
        }

        MealPlan mealPlan = mealPlanRepository.findById(mealPlanId)
                .orElseThrow(() -> new RuntimeException("Meal plan not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Rating newRating = new Rating();
        newRating.setMealPlan(mealPlan);
        newRating.setUser(user);
        newRating.setRating(rating);
        return ratingRepository.save(newRating);
    }

    public double getAverageRating(Long mealPlanId) {
        List<Rating> ratings = ratingRepository.findByMealPlanId(mealPlanId);
        return ratings.stream().mapToInt(Rating::getRating).average().orElse(0.0);
    }
}
