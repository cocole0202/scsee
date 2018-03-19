package com.scuse.mapper;

import com.scuse.entity.Ticket;

public interface TicketMapper {
    int insert(Ticket record);

    int insertSelective(Ticket record);
}