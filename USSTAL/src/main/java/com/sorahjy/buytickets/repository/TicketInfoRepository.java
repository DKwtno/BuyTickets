package com.sorahjy.buytickets.repository;

import com.sorahjy.buytickets.dataobject.TicketInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketInfoRepository extends JpaRepository<TicketInfo,String> {
    // List<TicketInfo> findByProductStatus(Integer productStatus);
    Page<TicketInfo> findAllByAirlineType (Integer airlineType, Pageable pageable);
}
