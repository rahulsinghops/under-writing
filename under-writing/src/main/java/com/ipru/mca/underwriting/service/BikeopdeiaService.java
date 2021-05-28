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

import com.ipru.mca.underwriting.entity.BikeDetails;
import com.ipru.mca.underwriting.logging.LoggingRepo;
import com.ipru.mca.underwriting.logging.UWLogging;
import com.ipru.mca.underwriting.model.BikeBO;
import com.ipru.mca.underwriting.model.BikeInput;
import com.ipru.mca.underwriting.model.BikeOutput;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class BikeopdeiaService {
	
	Logger log = LoggerFactory.getLogger(BikeopdeiaService.class);
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	LoggingRepo logging;
	//Bikeopdeia call service
	@HystrixCommand(fallbackMethod = "BikeopediaDetailsFallback",
			commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "15000") 
			                     })
	public BikeOutput getBikeopediaDetails(BikeInput input)
	{
		log.info("getBikeopediaDetails service called,input:"+ input);
		//db log
		UWLogging logdata=  logging.save(new UWLogging("getBikeopediaDetails", input.toString(), null, null, new Date()));
		//try {
		//rest call
			HttpHeaders header = new HttpHeaders();
			header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			//header.set("Content-Type", "application/json");
			HttpEntity<BikeInput> httpentity = new HttpEntity<BikeInput>(input,header);
			//ResponseEntity<BreOutputDetails> entity= restTemplate.exchange("http://BRE-SERVICE/mca/bre", HttpMethod.POST,httpentity, BreOutputDetails.class, input);
			ResponseEntity<BikeOutput> entity= restTemplate.exchange("http://bike-o-pedia/mca/bikes", HttpMethod.POST,httpentity, BikeOutput.class);
		//ResponseEntity<?> entity=  restTemplate.getForEntity("http://localhost:8092/mca/bikes", BikeOutput.class, input);
		if(entity.getStatusCode().toString().equals("202 ACCEPTED"))
		{
			BikeOutput output=(BikeOutput) entity.getBody();
			log.info("getBikeopediaDetails service executed,output:"+output);
			logdata.setResponse(output.toString());
			logging.save(logdata);
			return output;
		}
		else
		{
			log.info("getBikeopediaDetails service executed without success");
			return new BikeOutput();
		}
		/*
		 * } catch(Exception e) {
		 * log.info("getBikeopediaDetails service failed ,error:"+e.getMessage());
		 * logdata.setError(e.getMessage()); logging.save(logdata); return new
		 * BikeOutput(); }
		 */
		
	}

	
	
	//fallbackMethod 
	public BikeOutput BikeopediaDetailsFallback(BikeInput input)
	{
		log.info("BikeopediaDetailsFallback service called,input:"+ input);
		//db log
		UWLogging logdata=  logging.save(new UWLogging("BikeopediaDetailsFallback", input.toString(), null, null, new Date()));
		BikeBO bikedetails = new BikeBO("gixxer", "new", 60000, "well performed commuter/economy bike");
		BikeOutput output = new BikeOutput(input.getCity(), input.getType(), "200",Arrays.asList(bikedetails) );
		logdata.setResponse(output.toString());
		logging.save(logdata);
		return output;
	}
}
