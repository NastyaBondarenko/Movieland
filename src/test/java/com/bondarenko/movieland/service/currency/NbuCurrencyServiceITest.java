package com.bondarenko.movieland.service.currency;

import com.bondarenko.movieland.service.entity.common.Currency;
import com.bondarenko.movieland.service.entity.common.CurrencyType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class NbuCurrencyServiceITest {
    private List<Currency> bankCurrency;

    @Autowired
    private NbuCurrencyService currencyService;

    @BeforeAll
    public void setUp() {
        currencyService = new NbuCurrencyService();
        bankCurrency = currencyService.getBankCurrency();
    }

    @Test
    @DisplayName("when Get Bank Currency then Appropriate Currency Name Return")
    public void whenGetBankCurrency_thenAppropriateCurrencyNameReturn() {

        assertEquals("Долар США", bankCurrency.get(24).getCurrencyName());
        assertEquals("Євро", bankCurrency.get(31).getCurrencyName());
        assertEquals("Австралійський долар", bankCurrency.get(0).getCurrencyName());
        assertEquals("Канадський долар", bankCurrency.get(1).getCurrencyName());
        assertEquals("Форинт", bankCurrency.get(6).getCurrencyName());
    }

    @Test
    @DisplayName("when Get Bank Currency then Appropriate Type Return")
    public void whenGetBankCurrency_thenAppropriateTypeReturn() {

        assertEquals("USD", bankCurrency.get(24).getCurrencyType());
        assertEquals("EUR", bankCurrency.get(31).getCurrencyType());
        assertEquals("AUD", bankCurrency.get(0).getCurrencyType());
        assertEquals("CAD", bankCurrency.get(1).getCurrencyType());
        assertEquals("HUF", bankCurrency.get(6).getCurrencyType());
    }

    @Test
    @DisplayName("when Get Bank Currency then Appropriate Serial Number Return")
    public void whenGetBankCurrency_thenAppropriateSerialNumberReturn() {

        assertEquals(840, bankCurrency.get(24).getSerialNumber());
        assertEquals(978, bankCurrency.get(31).getSerialNumber());
        assertEquals(36, bankCurrency.get(0).getSerialNumber());
        assertEquals(124, bankCurrency.get(1).getSerialNumber());
        assertEquals(348, bankCurrency.get(6).getSerialNumber());
    }

    @Test
    @DisplayName("when Get Bank Currency then Correct Size Of Currency Return")
    public void whenGetBankCurrency_thenCorrectSizeOfCurrencyReturn() {
        assertNotNull(bankCurrency);
        assertEquals(61, bankCurrency.size());
    }

    @Test
    @DisplayName("when Convert Price then Not Zero Price Return")
    public void whenConvertPrice_thenNotZeroPriceReturn() {
        double currentPrise = 100;
        double convertedPrise = currencyService.convertPrice(currentPrise, CurrencyType.EUR);
        assertNotEquals(0.00, convertedPrise);
    }
}