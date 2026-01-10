package dev.caio.fitsy.model.Food;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToOne
    @JoinColumn(name="dailyFood_id")
    private DailyFood dailyFood;

    @OneToMany(mappedBy = "meal")
    private List<MealFood> mealFood;

}
