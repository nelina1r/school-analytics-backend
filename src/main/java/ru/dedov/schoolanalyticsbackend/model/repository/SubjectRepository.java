package ru.dedov.schoolanalyticsbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dedov.schoolanalyticsbackend.model.entity.Subject;

import java.util.Optional;

/**
 * Репозиторий для сущности "Предмет"
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

	boolean existsByNameIgnoreCase(String name);

	Optional<Subject> findByNameIgnoreCase(String name);
}
