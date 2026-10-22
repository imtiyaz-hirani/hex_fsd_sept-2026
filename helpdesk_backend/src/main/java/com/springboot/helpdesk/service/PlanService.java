package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.PlanDto;
import com.springboot.helpdesk.mapper.PlanMapper;
import com.springboot.helpdesk.model.Plan;
import com.springboot.helpdesk.repository.PlanRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanService {

    private final PlanRepository planRepository;

     @Transactional( rollbackFor = Exception.class)
    public void addPlansInBatch(List<PlanDto> list) {
       List<Plan> listEntity =   list.stream()
                .map(PlanMapper :: mapDtoToEntity)
                .toList();

       log.info("Processing multiple entities");
       planRepository.saveAll(listEntity);
    }
}
