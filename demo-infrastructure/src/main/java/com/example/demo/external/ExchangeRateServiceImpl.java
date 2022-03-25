package com.example.demo.external;

import com.example.demo.types.Currency;
import com.example.demo.types.ExchangeRate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{
    @Override
    public ExchangeRate getExchangeRate(Currency source, Currency target) {
//        if (source.equals(target)) {
            return new ExchangeRate(BigDecimal.ONE, source, target);
//        }

    }
}
