package dev.caio.fitsy.model;

import dev.caio.fitsy.model.enums.Estrategia;
import dev.caio.fitsy.model.enums.NivelAtividade;
import dev.caio.fitsy.model.enums.Objetivo;
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
@Table(name = "meta")
@Entity
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @Column(name = "nivel_atividade", length = 20)
    @Enumerated(EnumType.STRING)
    private NivelAtividade nivelAtividade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Objetivo objetivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Estrategia estrategia;

    @Column(name = "peso_inicial", nullable = false)
    private Float pesoInicial;

    @Column(name = "peso_meta", nullable = false)
    private Float pesoMeta;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @OneToMany(mappedBy = "meta")
    private List<MetaNutrientes> metaNutrientes;
}

