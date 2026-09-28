package com.sece.examcraft.repositories;

import com.sece.examcraft.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByUnitAndDifficulty(
            String unit,
            String difficulty);

    List<Question> findByUnitAndTopic(
            String unit,
            String topic);

    List<Question> findByUnitAndTopicAndDifficulty(
            String unit,
            String topic,
            String difficulty);

    List<Question> findByUnitAndTopicInAndDifficulty(
            String unit,
            List<String> topics,
            String difficulty);
}