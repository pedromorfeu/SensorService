package org.sensorservice.service;

import java.net.URISyntaxException;
import java.util.Date;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ParticleSensorService implements SensorService {

	@Cacheable("temperature")
	public double getTemperature() throws RestClientException, URISyntaxException, InterruptedException {
		RestTemplate restTemplate = new RestTemplate();
		// ResponseEntity<ParticleVariableResponse> response =
		// restTemplate.exchange(URL, HttpMethod.GET, null,
		// ParticleVariableResponse.class, deviceId, accessToken);
		// return response.getBody().getResult();
		System.out.println(new Date() + " temperature");
		Thread.sleep(5000);
		System.out.println(new Date() + " returning ");
		return 22.0;
	}
}
