package dev.caio.fitsy.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nutrientes")
@Entity
public class Nutriente {

    @Id
    @Column(name = "alimento_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "alimento_id")
    private Alimento alimento;

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
}

