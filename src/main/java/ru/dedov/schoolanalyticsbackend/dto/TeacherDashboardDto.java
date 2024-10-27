package ru.dedov.schoolanalyticsbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * ДТО дашборда преподавателя
 *
 * @author Alexander Dedov
 * @since 27.10.2024
 */
@Data
@Schema(description = "Объект дашборда по классу")
@AllArgsConstructor
public class TeacherDashboardDto {

	@JsonProperty("class_average_attendance")
	@Schema(description = "Средний процент посещаемости по классу", example = "85")
	private Double classAverageAttendance;

	@JsonProperty("class_average_grade")
	@Schema(description = "Средний балл по классу", example = "4.0")
	private Double classAverageGrade;

	@JsonProperty("student_metrics")
	@Schema(description = "Список показателей по каждому ученику")
	private List<StudentMetricsDto> studentMetrics;
}
