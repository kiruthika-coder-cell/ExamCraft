package com.sece.examcraft.repositories;

import com.sece.examcraft.entities.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
}