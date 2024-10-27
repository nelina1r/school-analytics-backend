package ru.dedov.schoolanalyticsbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	@JoinColumn(name = "teacher_id")
	@JsonIgnoreProperties({"assignedClasses"})
	private Teacher teacher;
	/**
	 * Ученики класса
	 */
	@OneToMany(mappedBy = "aClass")
	@JsonManagedReference
	private List<Student> students;
}
