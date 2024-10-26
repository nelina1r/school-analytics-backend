package ru.dedov.schoolanalyticsbackend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Сущность "Ученик"
 *
 * @author Alexander Dedov
 * @since 19.10.2024
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student extends User {

	/**
	 * Класс, в котором учится ученик
	 */
	@ManyToOne
	@JoinColumn(name = "school_class_id")
	private Class aClass;
	/**
	 * Оценки по предметам
	 */
	@OneToMany(mappedBy = "student")
	private List<Grade> grades;
	/**
	 * Посещения
	 */
	@OneToMany(mappedBy = "student")
	private List<Attendance> attendances;
	/**
	 * SEL данные студента
	 */
	@OneToMany(mappedBy = "student")
	private List<SELData> selData;
}
