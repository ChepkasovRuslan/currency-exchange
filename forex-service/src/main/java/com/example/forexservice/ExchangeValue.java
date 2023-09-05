package com.example.forexservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeValue {

  @Id
  private Long id;

  @Column(name = "currency_from")
  private String from;

  @Column(name = "currency_to")
  private String to;

  @Column(name = "conversion_multiple")
  private BigDecimal conversionMultiple;

  @Column(name = "port")
  private int port;
}