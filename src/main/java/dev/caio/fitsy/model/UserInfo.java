package dev.caio.fitsy.model;

import dev.caio.fitsy.model.enums.Sexo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_info")
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Float altura;

    @Column
    private Float peso;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Sexo sexo;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY)
    private List<HistoricoPeso> historicoPeso;

    @OneToMany(mappedBy = "userInfo")
    private List<DiarioAlimentar> diarioAlimentar;

    @OneToMany(mappedBy = "userInfo")
    private List<Meta> metas;
}

