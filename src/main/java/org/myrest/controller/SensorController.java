package org.myrest.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.myrest.data.ParticleVariableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableAutoConfiguration
public class SensorController {

  private static final String URL = "https://api.particle.io/v1/devices/{device_id}/temp/?access_token={access_token}";

  private String deviceId;
  private String accessToken;

  @Autowired
  public SensorController(ApplicationArguments args) {
    List<String> nonOptionArgs = args.getNonOptionArgs();
    deviceId = nonOptionArgs.get(0);
    accessToken = nonOptionArgs.get(1);
  }

  @RequestMapping("/sensor/temperature")
  @ResponseBody
  double temperature() throws RestClientException, URISyntaxException {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<ParticleVariableResponse> response = restTemplate.exchange(URL, HttpMethod.GET, null,
        ParticleVariableResponse.class, deviceId, accessToken);
    return response.getBody()
                   .getResult();
  }

  public static void main(String[] args) {
    SpringApplication.run(SensorController.class, args);
  }
}
