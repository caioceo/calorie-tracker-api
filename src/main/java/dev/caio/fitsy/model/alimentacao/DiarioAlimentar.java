package dev.caio.fitsy.model.alimentacao;

import dev.caio.fitsy.model.user.UserInfo;
import dev.caio.fitsy.model.metas.MetaNutrientes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diario_alimentar")
@Entity
public class DiarioAlimentar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "meta_nutrientes_id", nullable = false)
    private MetaNutrientes metaNutrientes;

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataRegistro;

    @OneToMany(mappedBy = "diarioAlimentar", cascade = CascadeType.ALL)
    private List<Refeicao> refeicoes;

    @PrePersist
    protected void onCreate() {
        if (dataRegistro == null) {
            dataRegistro = LocalDate.now();
        }
    }
}

