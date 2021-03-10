package com.ads.apigm.mock.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
  private final String token;
  private final long id;
  private final String name;

}
