package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.entity.Currency;
import com.bondarenko.movieland.entity.CurrencyType;
import com.bondarenko.movieland.exceptions.CurrencyNotFoundException;
import com.bondarenko.movieland.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCurrencyService implements CurrencyService {
    private final WebClient webClient;

    @Override
    public double convertPrice(double price, CurrencyType currencyType) {
        List<Currency> currencyList = getBankCurrency();
        if (currencyType != CurrencyType.UAH) {
            double bankRate = getBankRate(currencyType, currencyList);
            return BigDecimal.valueOf(price / bankRate)
                    .doubleValue();
        }
        return price;
    }

    private List<Currency> getBankCurrency() {
        Mono<Currency[]> response = webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Currency[].class).log();
        Currency[] currencies = response.block();
        return List.of(currencies);
    }

    private double getBankRate(CurrencyType currencyType, List<Currency> currencyList) {
        return currencyList.stream()
                .filter(currency -> currency.getCurrencyType().equals(currencyType.getType()))
                .map(Currency::getRate).findFirst().orElseThrow(() -> new CurrencyNotFoundException(currencyType));
    }
}