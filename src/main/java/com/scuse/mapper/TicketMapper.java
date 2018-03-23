package com.scuse.mapper;

import com.scuse.entity.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketMapper {
    int insert(Ticket record);

    int insertSelective(Ticket record);
}