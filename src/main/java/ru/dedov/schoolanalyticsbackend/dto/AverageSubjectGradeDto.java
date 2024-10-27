package ru.dedov.schoolanalyticsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO средней оценки ученика по предмету
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Data
@AllArgsConstructor
public class AverageSubjectGradeDto {

	private String subject;
	private Double averageGrade;
}
