package dev.caio.fitsy.model.Food;

import dev.caio.fitsy.model.Client.Client;
import dev.caio.fitsy.model.Client.Goal;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="nutrients_goal")
public class NutrientsGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(mappedBy = "nutrientsGoal")
    private Goal goal;

    @Column(name = "kcal")
    private Double calories;
    @Column(name = "protein")
    private Double protein;
    @Column(name = "lipids")
    private Double fat;
    @Column(name = "carbohydrates")
    private Double carbs;
    @Column(name = "dietary_fiber")
    private Double fiber;
    @Column(name = "cholesterol")
    private Double cholesterol;
    @Column(name = "sodium")
    private Double sodium;
    @Column(name = "potassium")
    private Double potassium;
    @Column(name = "iron")
    private Double iron;
    @Column(name = "calcium")
    private Double calcium;
    @Column(name="zinc")
    private Double zinc;
    @Column(name="vitamin_c")
    private Double vitamin_c;
    @Column(name="magnesium")
    private Double magnesium;
}


