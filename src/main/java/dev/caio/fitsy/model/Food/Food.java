package dev.caio.fitsy.model.Food;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food")

public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // food_category_id
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @OneToOne(mappedBy = "food")
    private MealFood mealFood;

    @OneToOne(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private Nutrients nutrients;
}