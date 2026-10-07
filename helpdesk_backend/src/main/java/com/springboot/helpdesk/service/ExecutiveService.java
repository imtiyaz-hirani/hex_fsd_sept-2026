package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.ExecutiveDto;
import com.springboot.helpdesk.enums.Role;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.mapper.ExecutiveMapper;
import com.springboot.helpdesk.model.Executive;
import com.springboot.helpdesk.model.Manager;
import com.springboot.helpdesk.model.User;
import com.springboot.helpdesk.repository.ExecutiveRepository;
import com.springboot.helpdesk.repository.ManagerRepository;
import com.springboot.helpdesk.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExecutiveService {
    private final ExecutiveRepository executiveRepository;
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;

    public void add(@Valid ExecutiveDto executiveDto, Long managerId) {
        // Step 0: Fetch manager from DB using managerId
        Optional<Manager> optional =  managerRepository.findById(managerId);
        if(optional.isEmpty())
            throw new ResourceNotFoundException("Manager id Invalid");
        Manager manager = optional.get();

        // Step 1: Prepare User object and save it in DB
        /*
        Executive
            User user <-- User must go in DB first and then Executive next
        * */
        User user = new User();
        user.setUsername(executiveDto.username());
        user.setPassword(executiveDto.password());
        user.setRole(Role.EXECUTIVE);
        user = userRepository.save(user); // <-- this is a full created user obj with id

        // Step Map ExecutiveDto to Executive entity
        Executive executive = ExecutiveMapper.mapDtoToEntity(executiveDto);

        // Step 3: Attach the User & Manager to Executive
        executive.setUser(user);
        executive.setManager(manager);

        // Step 4: Save executive and return
        executiveRepository.save(executive);
    }
}
