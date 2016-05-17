package org.sensorservice.service;

import java.net.URISyntaxException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.sensorservice.data.ParticleVariableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ParticleSensorService implements SensorService {

	private static final String URL = "https://api.particle.io/v1/devices/{device_id}/{variable}/?access_token={access_token}";

	private String deviceId;
	private String accessToken;

	@Autowired
	private ApplicationArguments args;

	@PostConstruct
	private void setVariables() {
		deviceId = args.getNonOptionArgs().get(1);
		accessToken = args.getNonOptionArgs().get(2);
	}

	@Cacheable(cacheManager = "cacheManager", cacheNames = { "temperature" })
	public double getTemperature() throws RestClientException, URISyntaxException, InterruptedException {
		System.out.println(new Date() + " getTemp");
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("Making temperature request to Particle...");
		String variable = "temp";
		ResponseEntity<ParticleVariableResponse> response = restTemplate.exchange(URL, HttpMethod.GET, null, ParticleVariableResponse.class, deviceId, variable, accessToken);
		return response.getBody().getResult();
	}

	@Cacheable(cacheManager = "cacheManager", cacheNames = { "humidity" })
	public double getHumidity() throws RestClientException, URISyntaxException, InterruptedException {
		System.out.println(new Date() + " getTemp");
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("Making humidity request to Particle...");
		String variable = "humi";
		ResponseEntity<ParticleVariableResponse> response = restTemplate.exchange(URL, HttpMethod.GET, null, ParticleVariableResponse.class, deviceId, variable, accessToken);
		return response.getBody().getResult();
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
