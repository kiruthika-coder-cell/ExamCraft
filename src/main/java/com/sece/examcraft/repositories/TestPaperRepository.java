package com.sece.examcraft.repositories;

import com.sece.examcraft.entities.TestPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestPaperRepository
        extends JpaRepository<TestPaper, Long> {

    @Query(value = """
            SELECT question_id, COUNT(test_paper_id)
            FROM test_paper_questions
            GROUP BY question_id
            ORDER BY COUNT(test_paper_id) DESC
            """, nativeQuery = true)
    List<Object[]> getQuestionUsageFrequency();
}