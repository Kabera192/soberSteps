package com.kabera.sobersteps.repository;

import com.kabera.sobersteps.model.LogEntry;
import com.kabera.sobersteps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
    List<LogEntry> findByUser(User user);
}
