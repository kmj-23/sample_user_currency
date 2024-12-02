package com.sparta.currency_user.dto;

import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
public class ExchangeResponseDto {

    private Long id;

    private BigDecimal amountAfterExchange;

    private String status;

    private String currencyName;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


    public ExchangeResponseDto(Long id, BigDecimal amountAfterExchange, String status, String currencyName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
        this.currencyName = currencyName;
        this.createdAt = createdAt;
        this.modifiedAt =modifiedAt;
    }

    public ExchangeResponseDto(Long id, String status){
        this.id = id;
        this.status = status;
    }
}
