package com.ads.apigm.mock.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
  private final String token;
  private final Long id;
  private final String name;
  private final Long measureId;
}
