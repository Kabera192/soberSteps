package com.kabera.sobersteps.service;

import com.kabera.sobersteps.model.User;
import org.springframework.stereotype.Service;

@Service
public interface LogEntryService {
    void saveLogEntry(User user, String activity, String description);
}
