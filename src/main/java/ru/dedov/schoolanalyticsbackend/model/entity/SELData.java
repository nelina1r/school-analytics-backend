package ru.dedov.schoolanalyticsbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Сущность "SEL-данные"
 *
 * @author Alexander Dedov
 * @since 19.10.2024
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sel_data")
public class SELData {

	/**
	 * Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Ученик
	 */
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;
	/**
	 * Дата сбора данных
	 */
	@Column(name = "date", nullable = false)
	private LocalDateTime date;
	/**
	 * Оценка эмоционального состояния
	 */
	@Column(name = "emotional_score", nullable = false)
	private Integer emotionalScore;
	/**
	 * Оценка социального состояния
	 */
	@Column(name = "social_score", nullable = false)
	private Integer socialScore;
}
