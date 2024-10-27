package ru.dedov.schoolanalyticsbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@JsonIgnoreProperties({"students", "teacher", "id"})
	private Class aClass;
	/**
	 * Оценки по предметам
	 */
	@OneToMany(mappedBy = "student")
	@JsonManagedReference
	private List<Grade> grades;
	/**
	 * Посещения
	 */
	@OneToMany(mappedBy = "student")
	@JsonManagedReference
	private List<Attendance> attendances;
	/**
	 * SEL данные студента
	 */
	@OneToMany(mappedBy = "student")
	private List<SELData> selData;
}
