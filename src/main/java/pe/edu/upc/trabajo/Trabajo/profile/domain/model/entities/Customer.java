package pe.edu.upc.trabajo.Trabajo.profile.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;

@Data
@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long age;
    private Float height;
    private Float weight;
    private String objective;
    private String allergies;
    private String preferences;
    private String available_ingredient;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Customer(){}
    public Customer(Long id, Long age, Float height, Float weight, String objective, String allergies, String preferences, String available_ingredient) {
        this.id = id;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.objective = objective;
        this.allergies = allergies;
        this.preferences = preferences;
        this.available_ingredient = available_ingredient;
    }
}
