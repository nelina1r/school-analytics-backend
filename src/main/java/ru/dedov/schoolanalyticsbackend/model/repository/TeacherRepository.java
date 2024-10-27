package ru.dedov.schoolanalyticsbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dedov.schoolanalyticsbackend.model.entity.Teacher;

/**
 * Репозиторий для сущности "Учитель"
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
