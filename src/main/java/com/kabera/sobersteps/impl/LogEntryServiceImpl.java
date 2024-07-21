package com.kabera.sobersteps.impl;

import com.kabera.sobersteps.model.LogEntry;
import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.repository.LogEntryRepository;
import com.kabera.sobersteps.service.LogEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LogEntryServiceImpl implements LogEntryService {

    private final LogEntryRepository logEntryRepository;

    @Override
    public void saveLogEntry(User user, String activity, String description) {
        if (description != null && !description.trim().isEmpty()) {
            LogEntry log = new LogEntry();
            log.setUser(user);
            log.setActivity(activity);
            log.setDescription(description);
            log.setEntryDate(LocalDate.now());
            logEntryRepository.save(log);
        }
    }
}
