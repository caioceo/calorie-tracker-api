package dev.caio.fitsy.model.Client;

import dev.caio.fitsy.model.Enum.GoalObjective;
import dev.caio.fitsy.model.Enum.GoalStrategy;
import dev.caio.fitsy.model.Enum.Status;
import dev.caio.fitsy.model.Food.NutrientsGoal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "goal")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private LocalDate registerDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "strategy", nullable = true)
    private GoalStrategy strategy;

    @Enumerated(EnumType.STRING)
    @Column(name = "objective", nullable = true)
    private GoalObjective objective;

    @Column(nullable = false)
    private Double initialWeight;
    @Column(nullable = false)
    private Double targetWeight;
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name="nutrientsGoal_id")
    private NutrientsGoal nutrientsGoal;

}

