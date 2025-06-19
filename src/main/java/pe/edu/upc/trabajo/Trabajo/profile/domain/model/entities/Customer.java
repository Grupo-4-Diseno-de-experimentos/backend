package pe.edu.upc.trabajo.Trabajo.profile.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
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


}
