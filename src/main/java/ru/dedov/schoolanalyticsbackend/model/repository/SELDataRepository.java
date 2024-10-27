package ru.dedov.schoolanalyticsbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dedov.schoolanalyticsbackend.model.entity.SELData;

/**
 * Репозиторий для сущности "SEL-данные"
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Repository
public interface SELDataRepository extends JpaRepository<SELData, Long> {
}
