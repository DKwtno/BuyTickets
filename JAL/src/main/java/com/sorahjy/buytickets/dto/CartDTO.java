package com.sorahjy.buytickets.dto;

import lombok.Data;

@Data
public class CartDTO {
    private String ticketId;

    private Integer ticketQuantity;

    public CartDTO(final String ticketId, final Integer ticketQuantity) {
        this.ticketId = ticketId;
        this.ticketQuantity = ticketQuantity;
    }
}
