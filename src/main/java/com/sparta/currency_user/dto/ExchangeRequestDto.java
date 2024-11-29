package com.sparta.currency_user.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    private Long userId;

    private BigDecimal amountInKrw;

    private String currencyName;

    public ExchangeRequestDto(Long userId, BigDecimal amountInKrw, String currencyName) {
        this.userId = userId;
        this.amountInKrw = amountInKrw;
        this.currencyName = currencyName;
    }
}
