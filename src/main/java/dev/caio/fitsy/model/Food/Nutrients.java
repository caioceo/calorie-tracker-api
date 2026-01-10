package dev.caio.fitsy.model.Food;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nutrients")
public class Nutrients {


    @Id
    @Column(name = "food_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name="food_id")
    private Food food;

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
