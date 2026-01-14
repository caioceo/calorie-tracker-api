package dev.caio.fitsy.model;

import dev.caio.fitsy.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meta_nutrientes")
@Entity
public class MetaNutrientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meta_id")
    private Meta meta;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @Column
    private Float calorias;

    @Column
    private Float proteinas;

    @Column
    private Float carboidratos;

    @Column
    private Float gorduras;

    @Column
    private Float fibras;

    @Column
    private Float colesterol;

    @Column
    private Float sodio;

    @Column
    private Float potassio;

    @Column
    private Float ferro;

    @Column
    private Float calcio;

    @Column
    private Float zinco;

    @Column(name = "vitamina_c")
    private Float vitaminaC;

    @Column
    private Float magnesio;

    @OneToMany(mappedBy = "metaNutrientes")
    private List<DiarioAlimentar> diariosAlimentares;

    @PrePersist
    protected void onCreate() {
        if (dataInicio == null) {
            dataInicio = LocalDate.now();
        }
    }
}

