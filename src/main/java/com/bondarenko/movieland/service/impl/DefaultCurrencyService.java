package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.exceptions.CurrencyNotFoundException;
import com.bondarenko.movieland.service.CurrencyService;
import com.bondarenko.movieland.service.entity.common.Currency;
import com.bondarenko.movieland.service.entity.common.CurrencyType;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCurrencyService implements CurrencyService {
    private final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?json";
    private final WebClient nbuClient = WebClient.create(NBU_URL);

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

    @Cacheable({"currency"})
    public List<Currency> getBankCurrency() {
        Mono<Currency[]> response = nbuClient.get()
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