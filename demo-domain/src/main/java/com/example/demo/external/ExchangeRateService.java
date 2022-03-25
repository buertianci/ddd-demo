package com.example.demo.external;

import com.example.demo.types.Currency;
import com.example.demo.types.ExchangeRate;

public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency source, Currency target);

}

