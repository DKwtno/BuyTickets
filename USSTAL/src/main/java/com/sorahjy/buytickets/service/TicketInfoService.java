package com.sorahjy.buytickets.service;

import com.sorahjy.buytickets.dataobject.TicketInfo;
import com.sorahjy.buytickets.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketInfoService {

    public TicketInfo findOne(String ticketid);
    public Page<TicketInfo> findAll(Pageable pageable);
    Page<TicketInfo> findUpAll(Integer airlineType, Pageable pageable);
    public TicketInfo save(TicketInfo ticketInfo);
    void increaseStock(List<CartDTO> cartDTOList);
    void decreaseStock(List<CartDTO> cartDTOList);

    void deleteTicket(String ticketid);

}
