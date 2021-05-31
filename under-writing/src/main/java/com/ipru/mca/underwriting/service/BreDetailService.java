package com.ipru.mca.underwriting.service;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ipru.mca.underwriting.logging.LoggingRepo;
import com.ipru.mca.underwriting.logging.UWLogging;
import com.ipru.mca.underwriting.model.BreInputDetails;
import com.ipru.mca.underwriting.model.BreOutputDetails;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class BreDetailService {

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	LoggingRepo logging;

	Logger log = LoggerFactory.getLogger(BreDetailService.class);

	// bre call service
	@HystrixCommand(fallbackMethod = "BreDetailsFallback", threadPoolKey = "BreThreadPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "20"),
			@HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "15000") })
	public BreOutputDetails getBreDetails(BreInputDetails input) {
		log.info("getBreDetails service called,input:" + input);
		// db log
		UWLogging logdata = logging.save(new UWLogging("getBreDetails", input.toString(), null, null, new Date()));

		// rest call
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		// header.set("Content-Type", "application/json");
		HttpEntity<BreInputDetails> httpentity = new HttpEntity<BreInputDetails>(input, header);
		// ResponseEntity<BreOutputDetails> entity=
		// restTemplate.exchange("http://BRE-SERVICE/mca/bre",
		// HttpMethod.POST,httpentity, BreOutputDetails.class, input);
		ResponseEntity<BreOutputDetails> entity = restTemplate.exchange("http://bre-service/mca/bre", HttpMethod.POST,
				httpentity, BreOutputDetails.class);
		// ResponseEntity<?> entity=
		// restTemplate.getForEntity("http://BRE-SERVICE/mca/bre",
		// BreOutputDetails.class, input);
		if (entity.getStatusCode().toString().equals("202 ACCEPTED")) {
			BreOutputDetails output = (BreOutputDetails) entity.getBody();
			log.info("getBreDetails service executed,output:" + output);
			logdata.setResponse(output.toString());
			logging.save(logdata);
			return output;
		} else {
			log.info("getBreDetails service executed without success");
			logdata.setError("return status code:" + entity.getStatusCode().toString());
			logging.save(logdata);
			return new BreOutputDetails();
		}
		/*
		 * catch (Exception e) { log.info("getBreDetails service failed ,error:" +
		 * e.getMessage()); logdata.setError("CatchBlockcalled :"+e.getMessage());
		 * logging.save(logdata); return new BreOutputDetails(); }
		 */

	}

	// fallback method
	public BreOutputDetails BreDetailsFallback(BreInputDetails input) {
		log.info("BreDetailsFallback service called,input:" + input);
		// db log
		UWLogging logdata = logging.save(new UWLogging("BreDetailsFallback", input.toString(), null, null, new Date()));
		BreOutputDetails output = new BreOutputDetails("ECONOMY", input.getLowerRange(), input.getUpperRange(), "200");
		logdata.setResponse(output.toString());
		logging.save(logdata);
		return output;

	}
}
