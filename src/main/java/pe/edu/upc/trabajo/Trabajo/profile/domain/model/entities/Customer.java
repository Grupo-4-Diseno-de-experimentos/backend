package pe.edu.upc.trabajo.Trabajo.profile.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.trabajo.Trabajo.profile.domain.model.typeObject.ActivityLevel;
import pe.edu.upc.trabajo.Trabajo.profile.domain.model.typeObject.DietType;
import pe.edu.upc.trabajo.Trabajo.profile.domain.model.typeObject.GoalCustomer;
import pe.edu.upc.trabajo.Trabajo.profile.domain.model.typeObject.Method;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GoalCustomer goal;

    @Enumerated(EnumType.STRING)
    private Method method;

    private String sexo;
    private Integer edad;
    private Integer altura;
    private Integer peso;

    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Enumerated(EnumType.STRING)
    private DietType dietType;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoalCustomer getGoal() {
        return goal;
    }

    public void setGoal(GoalCustomer goal) {
        this.goal = goal;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public DietType getDietType() {
        return dietType;
    }

    public void setDietType(DietType dietType) {
        this.dietType = dietType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}