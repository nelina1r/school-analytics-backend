package ru.dedov.schoolanalyticsbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dedov.schoolanalyticsbackend.model.entity.Attendance;
import ru.dedov.schoolanalyticsbackend.model.entity.Student;
import ru.dedov.schoolanalyticsbackend.model.entity.enums.AttendanceStatus;

/**
 * Репозиторий для сущности "Посещение"
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	long countByStudent(Student student);

	long countByStudentAndStatus(Student student, AttendanceStatus status);
}
