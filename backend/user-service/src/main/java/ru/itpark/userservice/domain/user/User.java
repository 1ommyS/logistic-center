package ru.itpark.userservice.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itpark.userservice.domain.user.VO.DateInfo;
import ru.itpark.userservice.domain.user.VO.Language;
import ru.itpark.userservice.domain.user.VO.Role;
import ru.itpark.userservice.domain.user.converters.LanguageConverter;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 200)
    private String fullName;

    @Column(name = "email", length = 255, unique = true)
    private String email;

    @Column(name = "login", length = 255, unique = true)
    private String login;

    @Column(name = "languages", columnDefinition = "jsonb")
    @Convert(converter = LanguageConverter.class)
    private List<Language> languages;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private DateInfo dateInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return login;
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

    public void update(
            String fullName,
            String email,
            String login,
            List<Language> languages,
            Role role,
            DateInfo dateInfo
    ) {
        this.fullName = fullName;
        this.email = email;
        this.login = login;
        this.languages = languages;
        this.role = role;
        this.dateInfo = dateInfo;
    }
}

