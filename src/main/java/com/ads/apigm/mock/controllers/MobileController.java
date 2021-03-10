package com.ads.apigm.mock.controllers;

import com.ads.apigm.mock.models.Actuation;
import com.ads.apigm.mock.models.Localization;
import com.ads.apigm.mock.models.Login;
import com.ads.apigm.mock.models.LoginResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MobileController {

  private final String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2MTUzNzQ2OTcsImV4cCI6MTYxOTc4MTA5NywiYXVkIjoiZ20tYXBwLWFzc2lzdGVkIiwic3ViIjoiNSJ9.Qb580wbz0oKANoWuu3IH2UiU3bYMW7aIZz_1n3EkYD0";
  private final Integer id = 1;
  private final String name = "Maria da Penha";
  //State simula o atendimento do chamado!
  private int state = 1;
  
  @PostMapping(value = "/mobile/login")
	public ResponseEntity<LoginResponse> userLogin(@RequestBody Login login) {
    
    if (login.getUsername() != null && login.getPassword() != null) {
      System.out.println("username: "+login.getUsername()+"\npassword: "+login.getPassword());
      LoginResponse response = new LoginResponse(token, id, name);
          System.out.println("sending response");
			return ResponseEntity.ok(response);
		}

    System.out.println("user not authorized");
		return ResponseEntity.status(401).body(null);
	}

  @PostMapping(value = "mobile/localization")
  public ResponseEntity<Object> updateLocalization(
    @RequestHeader(name = "Authorization") String token,
    @RequestBody Localization localization) {

    if (!token.equals(this.token)) {
      System.out.println("Unauthorized");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
    
    System.out.println("lat: "+localization.getPosition().get(0)+"\nlon: "+localization.getPosition().get(1));
    return ResponseEntity.ok().body("OK");
  }

  @GetMapping(value = "mobile/actuation/{id}")
  public ResponseEntity<Object> getActuation(
  @RequestHeader(name = "Authorization") String token,
  @PathVariable String id) {
    
    if (!token.equals(this.token)) {
      System.out.println("Unauthorized");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    if (!this.id.toString().equals(id)) {
      System.out.println("Not Found");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
    }

    Actuation actuation = new Actuation("42", String.valueOf(state));
    state = state%3 + 1;
    return ResponseEntity.ok().body(actuation);
  }
}
