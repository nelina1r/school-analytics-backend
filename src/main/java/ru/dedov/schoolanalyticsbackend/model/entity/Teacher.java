package ru.dedov.schoolanalyticsbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Сущность "Преподаватель"
 *
 * @author Alexander Dedov
 * @since 19.10.2024
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teachers")
public class Teacher extends User {

	/**
	 * Закрепленные классы
	 */
	@OneToMany(mappedBy = "teacher")
	private List<Class> assignedClasses;
	/**
	 * Преподаваемые предметы
	 */
	@ManyToMany
	@JoinTable(
		name = "teacher_subjects",
		joinColumns = @JoinColumn(name = "teacher_id"),
		inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private List<Subject> subjects;
}
