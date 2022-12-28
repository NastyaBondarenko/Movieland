package com.bondarenko.movieland.service;

import com.bondarenko.movieland.entity.CurrencyType;

public interface CurrencyService {
    double convertPrice(double price, CurrencyType currencyType);
}