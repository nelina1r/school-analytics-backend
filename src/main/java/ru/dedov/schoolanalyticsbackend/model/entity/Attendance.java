package ru.dedov.schoolanalyticsbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dedov.schoolanalyticsbackend.model.entity.enums.AttendanceStatus;

import java.time.LocalDateTime;

import static ru.dedov.schoolanalyticsbackend.model.entity.enums.AttendanceStatus.PRESENT;

/**
 * Сущность "Посещаемость"
 *
 * Определяет посещаемость ученика по каждому учебному предмету.
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance")
public class Attendance {

	/**
	 * Id посещаемости
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Ученик, для которого фиксируется посещаемость
	 */
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	/**
	 * Учебный предмет
	 */
	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;

	/**
	 * Дата посещаемости
	 */
	@Column(name = "date", nullable = false)
	private LocalDateTime date;

	/**
	 * Статус посещаемости (присутствует, отсутствует)
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "attendance_status", nullable = false)
	private AttendanceStatus status = PRESENT;
}
