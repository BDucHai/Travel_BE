package com.travel.repository;

import com.travel.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDestinationIdOrderByCreatedAtDesc(Long destinationId);
    List<Question> findByStatus(String status);
}
