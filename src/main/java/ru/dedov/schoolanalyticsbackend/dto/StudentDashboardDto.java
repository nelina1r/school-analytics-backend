package ru.dedov.schoolanalyticsbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * DTO дашборда по ученику
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Data
@Schema(description = "Объект дашборда по ученику")
public class StudentDashboardDto {

	@JsonProperty("general_attendance_percentile")
	@Schema(description = "Общий процент посещений ученика", example = "75")
	private Double generalAttendancePercentile;
	@JsonProperty("general_average_grade_value")
	@Schema(description = "Общий средний балл по всем предметам", example = "4.2")
	private Double generalAverageGradeValue;
	@JsonProperty("average_subject_grades")
	@Schema(description = "Средний балл по каждому предмету")
	private List<AverageSubjectGradeDto> averageSubjectGrades;
}
