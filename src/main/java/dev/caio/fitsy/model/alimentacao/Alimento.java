package dev.caio.fitsy.model.alimentacao;

import dev.caio.fitsy.model.user.Nutriente;
import dev.caio.fitsy.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alimentos")
@Entity
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "criador_id")
    private User criador;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaAlimento categoria;

    @Column(nullable = false, length = 100)
    private String nome;

    @OneToOne(mappedBy = "alimento", cascade = CascadeType.ALL)
    private Nutriente nutriente;

    @OneToMany(mappedBy = "alimento")
    private List<AlimentoRefeicao> alimentoRefeicoes;
}

