package com.ads.apigm.mock.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Localization {
  
  private final List<Double> position;
  private final boolean panic;
}
