package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.ManagerDto;
import com.springboot.helpdesk.enums.Role;
import com.springboot.helpdesk.mapper.ManagerMapper;
import com.springboot.helpdesk.model.Manager;
import com.springboot.helpdesk.model.User;
import com.springboot.helpdesk.repository.ManagerRepository;
import com.springboot.helpdesk.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;

    public Manager add(@Valid ManagerDto managerDto) {
        // Step 1: Prepare user object and save it in DB
        User user = new User();
        user.setUsername(managerDto.username());
        user.setPassword(managerDto.password());
        user.setRole(Role.MANAGER);
        user = userRepository.save(user); //<-- This user now has an id attached

        // Step 2: Map Dto to Entity
        Manager manager = ManagerMapper.convertDtoToEntity(managerDto);

        // Step 3: Attach User to Manager
        manager.setUser(user);

        // Step 4: save manager
        return managerRepository.save(manager);
    }
}
