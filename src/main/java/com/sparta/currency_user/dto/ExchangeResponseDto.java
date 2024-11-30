package com.sparta.currency_user.dto;

import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class ExchangeResponseDto {

    private Long id;

    private BigDecimal amountAfterExchange;

    private String status;

    private String currencyName;


    public ExchangeResponseDto(Long id, BigDecimal amountAfterExchange, String status, String currencyName) {
        this.id = id;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
        this.currencyName = currencyName;
    }

    public ExchangeResponseDto(Long id, String status){
        this.id = id;
        this.status = status;
    }
}
