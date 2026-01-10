package dev.caio.fitsy.model.Client;

import dev.caio.fitsy.model.Enum.Role;
import dev.caio.fitsy.model.Food.DailyFood;
import dev.caio.fitsy.model.Food.NutrientsGoal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private Double height;
    private Double weight;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "client")
    private List<Goal> goalList;
    @OneToMany(mappedBy = "client")
    private List<WeightHistory> weightHistoryList;
    @OneToMany(mappedBy = "client")
    private List<DailyFood> dailyFoodList;
}

