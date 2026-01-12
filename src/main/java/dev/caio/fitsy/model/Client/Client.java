package dev.caio.fitsy.model.Client;

import dev.caio.fitsy.model.Enum.Role;
import dev.caio.fitsy.model.Food.DailyFood;
import dev.caio.fitsy.model.Food.NutrientsGoal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private Double height;
    private Double weight;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "client")
    private List<Goal> goalList;
    @OneToMany(mappedBy = "client")
    private List<WeightHistory> weightHistoryList;
    @OneToMany(mappedBy = "client")
    private List<DailyFood> dailyFoodList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Role.PREMIUM) return List.of(new SimpleGrantedAuthority("ROLE_PREMIUM"), new SimpleGrantedAuthority("ROLE_FREEMIUM"));
        else return List.of(new SimpleGrantedAuthority("ROLE_FREEMIUM"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

