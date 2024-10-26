package ru.dedov.schoolanalyticsbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dedov.schoolanalyticsbackend.model.entity.Grade;
import ru.dedov.schoolanalyticsbackend.model.entity.Student;

import java.util.List;

/**
 * Репозиторий для сущности "Оценка"
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

	List<Grade> findByStudent(Student student);
}
