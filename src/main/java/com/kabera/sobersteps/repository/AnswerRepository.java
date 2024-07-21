package com.kabera.sobersteps.repository;

import com.kabera.sobersteps.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answers, Long> {
}
