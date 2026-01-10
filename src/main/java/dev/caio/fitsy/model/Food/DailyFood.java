package dev.caio.fitsy.model.Food;


import dev.caio.fitsy.model.Client.Client;
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
@Table(name = "daily_food")
public class DailyFood {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;
    private Boolean successful_day
    ;

    // client_id
    @ManyToOne
    @JoinColumn(name ="client_id")
    private Client client;
}
