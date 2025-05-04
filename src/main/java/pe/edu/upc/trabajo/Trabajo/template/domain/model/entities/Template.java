package pe.edu.upc.trabajo.Trabajo.template.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.trabajo.Trabajo.template.domain.model.aggregate.Food;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Template")
@Getter
@Setter
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Food food;

}
