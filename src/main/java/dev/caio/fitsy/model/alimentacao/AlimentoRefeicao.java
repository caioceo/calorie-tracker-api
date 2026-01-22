package dev.caio.fitsy.model.alimentacao;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alimento_refeicao")
@Entity
public class AlimentoRefeicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "refeicao_id")
    private Refeicao refeicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alimento_id")
    private Alimento alimento;

    @Column(nullable = false)
    private Float quantidade;

    @Column
    private Float calorias;

    @Column
    private Float proteinas;

    @Column
    private Float carboidratos;

    @Column
    private Float gorduras;

    public void setMacros(Alimento alimento){
        this.proteinas = alimento.getNutriente().getProteinas() * quantidade / 100;
        this.carboidratos = alimento.getNutriente().getCarboidratos() * quantidade / 100;
        this.gorduras = alimento.getNutriente().getGorduras() * quantidade / 100;
        this.calorias = alimento.getNutriente().getCalorias() * quantidade / 100;

    }
}

