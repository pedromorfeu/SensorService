package org.sensorservice.service;

import java.net.URISyntaxException;

import org.springframework.web.client.RestClientException;

public interface SensorService {

	public double getTemperature() throws RestClientException, URISyntaxException, InterruptedException;
}
