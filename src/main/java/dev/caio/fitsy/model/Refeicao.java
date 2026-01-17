package dev.caio.fitsy.model;

import dev.caio.fitsy.model.enums.RefeicaoNome;
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
    @Enumerated(EnumType.STRING)
    private RefeicaoNome nome;

    @OneToMany(mappedBy = "refeicao", cascade = CascadeType.ALL)
    private List<AlimentoRefeicao> alimentoRefeicoes;
}

