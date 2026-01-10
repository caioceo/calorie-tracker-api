package dev.caio.fitsy.model.Client;
import dev.caio.fitsy.model.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weight_history")
public class WeightHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double weight;
    private LocalDate registerDate;

    // client_id
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;


}

