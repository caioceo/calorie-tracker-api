package dev.caio.fitsy.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refeicoes")
@Entity
public class Refeicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diario_alimentar_id")
    private DiarioAlimentar diarioAlimentar;

    @Column(nullable = false, length = 50)
    private String nome;

    @OneToMany(mappedBy = "refeicao", cascade = CascadeType.ALL)
    private List<AlimentoRefeicao> alimentoRefeicoes;
}

