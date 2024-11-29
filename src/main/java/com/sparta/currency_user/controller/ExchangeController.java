package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @PostMapping
    public ResponseEntity<ExchangeResponseDto> exchange(@RequestBody ExchangeRequestDto exchangeRequestDto) {
        ExchangeResponseDto exchangeResponseDto =
                exchangeService.exchange(exchangeRequestDto.getUserId(), exchangeRequestDto.getAmountInKrw(), exchangeRequestDto.getCurrencyName());
        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity <List<ExchangeResponseDto>> findAllById(@RequestParam Long userId){// <List<>> ??
        List<ExchangeResponseDto> exchangeResponseDto = exchangeService.findAllById(userId);
        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.OK);
    }

//    @PutMapping("/{userId}") *
//    public ResponseEntity<ExchangeResponseDto> cancelExchange(@PathVariable Long exchangeId) {
//        ExchangeResponseDto exchangeResponseDto = exchangeService.cancelExchange(exchangeId);
//        return new ResponseEntity<>()
//    }
}
