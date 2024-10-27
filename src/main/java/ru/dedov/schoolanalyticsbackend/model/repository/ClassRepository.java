package ru.dedov.schoolanalyticsbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dedov.schoolanalyticsbackend.model.entity.Class;

/**
 * Репозиторий для сущности "Класс"
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

	boolean existsByNameIgnoreCase(String name);

	Class findByNameIgnoreCase(String name);
}
