package org.sensorservice.service;

import java.net.URISyntaxException;

import org.sensorservice.data.ParticleVariableResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ParticleSensorService implements SensorService {

	private static final String URL = "https://api.particle.io/v1/devices/{device_id}/temp/?access_token={access_token}";

	private String deviceId;
	private String accessToken;

	@Cacheable("temperature")
	public double getTemperature() throws RestClientException, URISyntaxException, InterruptedException {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ParticleVariableResponse> response = restTemplate.exchange(URL, HttpMethod.GET, null, ParticleVariableResponse.class, deviceId, accessToken);
		return response.getBody().getResult();
		// System.out.println(new Date() + " temperature");
		// Thread.sleep(5000);
		// System.out.println(new Date() + " returning ");
		// return 22.0;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
