package com.example.currencyconversionservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CurrencyConversionController {

  private final CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

  @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversionDto convertCurrency(
    @PathVariable String from,
    @PathVariable String to,
    @PathVariable BigDecimal quantity
  ) {
    CurrencyConversionDto response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
    log.info(response.toString());
    return new CurrencyConversionDto(response.getId(), from, to, response.getConversionMultiple(), quantity,
      quantity.multiply(response.getConversionMultiple()), response.getPort());
  }
}
