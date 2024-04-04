package com.nghia.springsecuritydemo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "USER_DETAIL", indexes = {
        @Index(name = "idx_email", columnList = "email"),
        @Index(name = "idx_reference", columnList = "reference")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User extends BaseEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
    private String reference;
    private boolean gender;
    private String password;
    private boolean isLocked;
    private boolean isAccountExpired;
    private boolean isEnabled;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(item -> new SimpleGrantedAuthority(item.getRole())).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
