package ru.dedov.schoolanalyticsbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * TODO Class Description
 *
 * @author Alexander Dedov
 * @since 27.10.2024
 */
@Data
@Schema(description = "Метрики ученика")
@AllArgsConstructor
public class StudentMetricsDto {

	@JsonProperty("student_name")
	@Schema(description = "ФИО ученика", example = "Иванов Иван Иванович")
	private String studentName;

	@JsonProperty("attendance_percent")
	@Schema(description = "Процент посещаемости", example = "90")
	private Double attendancePercent;

	@JsonProperty("average_grade")
	@Schema(description = "Средний балл по всем предметам", example = "4.5")
	private Double averageGrade;

	@JsonProperty("average_subject_grades")
	@Schema(description = "Средний балл по каждому предмету")
	private List<AverageSubjectGradeDto> averageSubjectGrades;
}
