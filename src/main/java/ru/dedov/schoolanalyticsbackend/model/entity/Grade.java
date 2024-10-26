package ru.dedov.schoolanalyticsbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Сущность "Оценка"
 *
 * @author Alexander Dedov
 * @since 19.10.2024
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grades")
public class Grade {

	/**
	 * Id оценки
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Ученик, получивший оценку
	 */
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	@JsonBackReference
	private Student student;
	/**
	 * Учебный предмет
	 */
	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	@JsonBackReference
	private Subject subject;
	/**
	 * Значение оценки
	 */
	@Column(name = "value", nullable = false)
	private Integer value;
	/**
	 * Дата выставления оценки
	 */
	@Column(name = "date", nullable = false)
	private LocalDateTime date;
}
