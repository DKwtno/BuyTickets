package com.sorahjy.buytickets.service.impl;

import com.sorahjy.buytickets.dataobject.TicketInfo;
import com.sorahjy.buytickets.dto.CartDTO;
import com.sorahjy.buytickets.enums.ResultEnum;
import com.sorahjy.buytickets.exception.SellException;
import com.sorahjy.buytickets.repository.TicketInfoRepository;
import com.sorahjy.buytickets.service.TicketInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketInfoServiceImpl implements TicketInfoService {

    @Autowired
    private TicketInfoRepository repository;

    @Override
    public TicketInfo findOne(final String ticketId) {
            return repository.findById(ticketId).get();
    }

    @Override
    public Page<TicketInfo> findAll(final Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<TicketInfo> findUpAll(final Integer airlineType,final Pageable pageable) {
        return repository.findAllByAirlineType(airlineType,pageable);
    }



    @Override
    public TicketInfo save(final TicketInfo ticketInfo) {
        return repository.save(ticketInfo);
    }


    @Override
    @Transactional
    public void increaseStock(final List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            TicketInfo ticketInfo=repository.findById(cartDTO.getTicketId()).get();
            Integer result=ticketInfo.getTicketStock()+cartDTO.getTicketQuantity();
            ticketInfo.setTicketStock(result);
            repository.save(ticketInfo);
        }
    }


    @Override
    @Transactional
    public void decreaseStock(final List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            TicketInfo ticketInfo=repository.findById(cartDTO.getTicketId()).get();
            if(ticketInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result=ticketInfo.getTicketStock()-cartDTO.getTicketQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            ticketInfo.setTicketStock(result);
            repository.save(ticketInfo);
        }

    }

    @Override
    public void deleteTicket(final String ticketid) {
        repository.deleteById(ticketid);
    }
}
