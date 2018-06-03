package com.sorahjy.buytickets.service;

import com.sorahjy.buytickets.dataobject.TicketInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketInfoService {

    public TicketInfo findOne(String ticketid);
    public Page<TicketInfo> findAll(Pageable pageable);
    List<TicketInfo> findUpAll();
    public TicketInfo save(TicketInfo ticketInfo);
    //TODO 加减库存

    //TODO 减库存
}
