package org.sensorservice.controller;

import java.net.URISyntaxException;

import org.sensorservice.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
@SpringBootApplication(scanBasePackages = { "org.sensorservice.*" })
@EnableCaching
public class SensorController {

	private static final String URL = "https://api.particle.io/v1/devices/{device_id}/temp/?access_token={access_token}";

	@Autowired
	private SensorService sensorService;

	@RequestMapping("/sensor/temperature")
	@ResponseBody
	double temperature() throws RestClientException, URISyntaxException, InterruptedException {
		return sensorService.getTemperature();
	}

	public static void main(String[] args) {
		SpringApplication.run(SensorController.class, args);
	}

}
