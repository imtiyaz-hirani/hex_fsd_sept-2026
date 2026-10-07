package com.springboot.helpdesk.controller;

import com.springboot.helpdesk.dto.request.ExecutiveDto;
import com.springboot.helpdesk.model.Executive;
import com.springboot.helpdesk.service.ExecutiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExecutiveController {

     private final ExecutiveService executiveService;

     /*
     DTO (name,email,contact)
     validation
     Map DTO to Entity
     Insert Entity IN Db
     * */
    @PostMapping("/api/executive/add")
    public Executive insertExecutive(@Valid @RequestBody ExecutiveDto executiveDto){
        return executiveService.add(executiveDto);
    }
}
