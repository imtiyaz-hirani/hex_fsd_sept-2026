package com.helpdesk.service;

import com.helpdesk.enums.Role;
import com.helpdesk.exception.ResourceNotFoundException;
import com.helpdesk.model.Executive;
import com.helpdesk.model.Manager;
import com.helpdesk.model.User;
import com.helpdesk.repository.ExecutiveRepository;
import com.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExecutiveService {

    private final ExecutiveRepository executiveRepository;
    private final UserRepository userRepository;

    public ExecutiveService(ExecutiveRepository executiveRepository, UserRepository userRepository) {
        this.executiveRepository = executiveRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void insertExecutive(int managerId, Executive executive, String username, String password) {
        // Step 1: Fetch Manager object from DB using managerId : validate managerId
        Optional<Manager> optional = executiveRepository.getManagerById(managerId);
        if(optional.isEmpty())
            throw new ResourceNotFoundException("ManagerId is invalid");

        Manager manager = optional.get();

        // Step 2: Prepare User object and insert it in db
        User user = new User(username,password,Role.EXECUTIVE); // <-- Prepared with 3 fields -- 100X
        userRepository.insert(user); //100X also has field with id
        /* Sep 2.5: Fetch entire user object by its username
        List<User> list=  userRepository.getUserByUsername(username); //<- this user has all 6 fields including id
         user = list.getFirst();
        */
        // Step 3: Attach user and manager to executive
        executive.setUser(user);
        executive.setManager(manager);

        // Step 4: pass executive to ExecutiveRepository
        executiveRepository.insert(executive);
    }
}
