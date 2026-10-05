package com.helpdesk.service;

import com.helpdesk.model.Executive;
import com.helpdesk.repository.ExecutiveRepository;
import org.springframework.stereotype.Service;

@Service
public class ExecutiveService {

    private final ExecutiveRepository executiveRepository;

    public ExecutiveService(ExecutiveRepository executiveRepository) {
        this.executiveRepository = executiveRepository;
    }

    public void insertExecutive(int managerId, Executive executive, String username, String password) {
        // Step 1: Prepare User object

        // Step 2: Fetch Manager object from DB using managerId

        // Step 3: Attach user and manager to executive

        // Step 4: pass executive to ExecutiveRepository
    }
}
