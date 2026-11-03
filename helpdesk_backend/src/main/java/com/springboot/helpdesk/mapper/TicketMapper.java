package com.springboot.helpdesk.mapper;

import com.springboot.helpdesk.dto.request.TicketDto;
import com.springboot.helpdesk.dto.response.ChartDto;
import com.springboot.helpdesk.dto.response.TicketRespDto;
import com.springboot.helpdesk.dto.response.TicketStatusStatDto;
import com.springboot.helpdesk.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketMapper {

    public static Ticket convertDtoToEntity(TicketDto dto){
        Ticket ticket = new Ticket();
        ticket.setSubject(dto.subject());
        ticket.setIssue(dto.issue());
        ticket.setPriority(dto.priority());
        return ticket;
    }

    public static TicketRespDto convertEntityToDto(Ticket ticket){
        return
                new TicketRespDto (
                        ticket.getId(),
                        ticket.getSubject(),
                        ticket.getCreatedAt(),
                        ticket.getPriority(),
                        ticket.getStatus(),
                        0L,
                        "",
                        0L,
                        ""
                );
    }

    public static ChartDto convertTicketToChartDto(List<TicketStatusStatDto> list){
        List<String> labels = new ArrayList<>();
        List<Long> values = new ArrayList<>();

        list.forEach(e->{
            labels.add(e.status().toString());
            values.add(e.numberOfCustomers());
        });

        return new ChartDto(
                labels,
                values
        );
    }
}
