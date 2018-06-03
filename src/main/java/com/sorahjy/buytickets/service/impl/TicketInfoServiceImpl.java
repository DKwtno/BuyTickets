package com.sorahjy.buytickets.service.impl;

import com.sorahjy.buytickets.dataobject.TicketInfo;
import com.sorahjy.buytickets.repository.TicketInfoRepository;
import com.sorahjy.buytickets.service.TicketInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketInfoServiceImpl implements TicketInfoService {

    @Autowired
    private TicketInfoRepository repository;

    @Override
    public TicketInfo findOne(final String ticketid) {
        return repository.findById(ticketid).get();
    }

    @Override
    public Page<TicketInfo> findAll(final Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<TicketInfo> findUpAll() {
        return repository.findAll();
    }

    @Override
    public TicketInfo save(final TicketInfo ticketInfo) {
        return repository.save(ticketInfo);
    }
}
