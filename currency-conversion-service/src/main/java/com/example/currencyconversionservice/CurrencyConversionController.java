package com.example.currencyconversionservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class CurrencyConversionController {

  @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversionDto convertCurrency(
    @PathVariable String from,
    @PathVariable String to,
    @PathVariable BigDecimal quantity
  ) {
    Map<String, String> uriVariables = new HashMap<>() {{
      put("from", from);
      put("to", to);
    }};

    ResponseEntity<CurrencyConversionDto> responseEntity = new RestTemplate().getForEntity(
      "http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionDto.class,
      uriVariables);

    CurrencyConversionDto response = responseEntity.getBody();
    return new CurrencyConversionDto(response.getId(), from, to, response.getConversionMultiple(), quantity,
      quantity.multiply(response.getConversionMultiple()), response.getPort());
  }
}