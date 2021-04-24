package com.example.producingwebservice;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
	
	private final Random rnd = new Random();

	private CountryRepository countryRepository;

	@Value("${response.delay.in.mills:#{null}}")
	private Long delayMills;
	
	@Value("${response.random.delay:false}")
	private boolean rndDelay;
	
	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) throws InterruptedException {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));
		Thread.sleep(getActualDelay());
		return response;
	}
	
	private long getActualDelay() {
		if (delayMills==null)
			return 0;
		if (rndDelay==false)
			return delayMills;
		return rnd.nextInt(Math.abs(delayMills.intValue()));
	}
}
