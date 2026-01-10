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
@Table(name = "meal_food")
public class MealFood {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Double amount;

    // meal_id
    @ManyToOne
    @JoinColumn(name="meal_id")
    private Meal meal;

    // food_id
    @OneToOne
    @JoinColumn(name="food_id")
    private Food food;
}
