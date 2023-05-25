package com.bondarenko.movieland.service;

import com.bondarenko.movieland.service.entity.common.CurrencyType;

public interface CurrencyService {
    double convertPrice(double price, CurrencyType currencyType);
}