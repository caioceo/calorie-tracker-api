package dev.caio.fitsy.model;

import dev.caio.fitsy.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "historico_peso")
@Entity
public class HistoricoPeso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @Column
    private Float peso;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataRegistro;

    @PrePersist
    protected void onCreate() {
        if (dataRegistro == null) {
            dataRegistro = LocalDate.now();
        }
    }
}

