package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeRepository;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    public ExchangeResponseDto exchange(
            Long userId,
            BigDecimal amountInKrw,
            String currencyName
    ) {

        User user = userRepository.findUserByIdOrElseThrow(userId);
        Currency currency = currencyRepository.findByCurrencyName(currencyName);

        BigDecimal amountAfterExchange = amountInKrw.divide(currency.getExchangeRate(), 2, RoundingMode.UP);

        Exchange exchange = new Exchange(amountInKrw, amountAfterExchange,"NORMAL", currency, user); // 상태를 나타낼때는 주로 대문자(?) EX. DELIVEVERED, ON_PROCESS..etc
        Exchange newExchange = exchangeRepository.save(exchange);

        return new ExchangeResponseDto(newExchange.getId(), amountAfterExchange, exchange.getStatus(), currencyName);
    }


    public List<ExchangeResponseDto> findAllById(Long userId) {

         List<Exchange> exchangeList = exchangeRepository.findAllByUserId(userId);

         if(exchangeList.isEmpty()) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "환전기록이 없습니다.");
         }

         return exchangeList.stream()
                 .map(exchange -> new ExchangeResponseDto(
                         exchange.getId(),
                         exchange.getAmountAfterExchange(),
                         exchange.getStatus(),
                         exchange.getCurrency().getCurrencyName()
                 ))
                 .toList();
    }

    public ExchangeResponseDto updateExchange(Long id) {// 메서드의 반환값이 전혀 사용되지 않았습니다.(?)
        Exchange updateExchange = exchangeRepository.findById(id) // Optional??
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 환전 기록이 없습니다"));

        updateExchange.setStatus("CANCEL");
        Exchange saveUpdateExchange = exchangeRepository.save(updateExchange);

        return new ExchangeResponseDto(saveUpdateExchange.getId() ,saveUpdateExchange.getStatus());
    }
}


