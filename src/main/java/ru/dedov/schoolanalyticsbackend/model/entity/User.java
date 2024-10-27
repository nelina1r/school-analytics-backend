package ru.dedov.schoolanalyticsbackend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.dedov.schoolanalyticsbackend.model.entity.enums.Role;

import java.util.Collection;
import java.util.List;

/**
 * Сущность "Пользователь"
 *
 * @author Alexander Dedov
 * @since 13.10.2024
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

	/**
	 * Id пользователя
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * ФИО пользователя
	 */
	@Column(name = "fio", nullable = false)
	private String fio; //нарушение атомарности ради демки
	/**
	 * Логин
	 */
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	/**
	 * Пароль
	 */
	@Column(name = "password", nullable = false)
	private String password;
	/**
	 * Почта
	 */
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	/**
	 * Роль
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
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
