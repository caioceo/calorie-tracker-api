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

    public void setNutrientes(Alimento alimento){
        this.proteinas = alimento.getNutriente().getProteinas() * quantidade / 100;
        this.carboidratos = alimento.getNutriente().getCarboidratos() * quantidade / 100;
        this.gorduras = alimento.getNutriente().getGorduras() * quantidade / 100;
        this.calorias = (alimento.getNutriente().getCalorias() * quantidade / 100);
        this.fibras = alimento.getNutriente().getFibras() * quantidade / 100;
        this.colesterol = alimento.getNutriente().getColesterol() * quantidade / 100;
        this.sodio = alimento.getNutriente().getSodio() * quantidade / 100;
        this.potassio = alimento.getNutriente().getPotassio() * quantidade / 100;
        this.ferro = alimento.getNutriente().getFerro() * quantidade / 100;
        this.calcio = alimento.getNutriente().getCalcio() * quantidade / 100;
        this.zinco = alimento.getNutriente().getZinco() * quantidade / 100;
        this.vitaminaC = alimento.getNutriente().getVitaminaC() * quantidade / 100;
        this.magnesio = alimento.getNutriente().getMagnesio() * quantidade / 100;
    }


}

