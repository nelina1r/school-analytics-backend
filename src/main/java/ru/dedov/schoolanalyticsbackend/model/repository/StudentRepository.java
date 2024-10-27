package ru.dedov.schoolanalyticsbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dedov.schoolanalyticsbackend.model.entity.Class;
import ru.dedov.schoolanalyticsbackend.model.entity.Student;

import java.util.List;

/**
 * Репозиторий для сущности "Ученик"
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE s.aClass = :clazz")
	List<Student> findByClass(Class clazz);
}
