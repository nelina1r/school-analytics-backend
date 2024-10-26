package ru.dedov.schoolanalyticsbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Сущность "Школьный предмет"
 *
 * @author Alexander Dedov
 * @since 19.10.2024
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subjects")
public class Subject {

	/**
	 * Id предмета
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Название предмета
	 */
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	/**
	 * Оценки по предмету
	 */
	@OneToMany(mappedBy = "subject")
	@JsonManagedReference
	private List<Grade> grades;
}
