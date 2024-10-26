package ru.dedov.schoolanalyticsbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Сущность "Класс"
 *
 * @author Alexander Dedov
 * @since 19.10.2024
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classes")
public class Class {

	/**
	 * Id класса
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Название класса
	 */
	@Column(name = "name", nullable = false)
	private String name;
	/**
	 * Закрепленный учитель
	 */
	@ManyToOne
	@JoinColumn(name = "teacher_id", nullable = false)
	private Teacher teacher;
	/**
	 * Ученики класса
	 */
	@OneToMany(mappedBy = "aClass")
	private List<Student> students;
}
