package com.ipru.mca.underwriting.controller;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipru.mca.underwriting.logging.LoggingRepo;
import com.ipru.mca.underwriting.logging.UWLogging;
import com.ipru.mca.underwriting.model.BikeOutput;
import com.ipru.mca.underwriting.model.UWException;
import com.ipru.mca.underwriting.model.UWInput;
import com.ipru.mca.underwriting.service.UWService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/mca")
public class UnderWritingService {
	@Autowired
	UWService service;

	@Autowired
	LoggingRepo logging;

	Logger log = LoggerFactory.getLogger(UnderWritingService.class);

	// for swagger documentation

	@ApiOperation(value = " To get bike suggestions", consumes = "application/json")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, path = "/uw")
	@ResponseBody
	public ResponseEntity<?> getUWDetails(@RequestBody @Valid UWInput request) {
		log.info("getUWDetails service invoked,request:" + request);
		UWLogging uwlog = logging.save(new UWLogging("getUWDetails", request.toString(), null, null, new Date()));
		try {
			BikeOutput response = service.getBikeDetails(request);
			log.info("getUWDetails service executed,request:" + response);
			// updating response in log
			uwlog.setResponse((response != null) ? response.toString() : "response is null");
			logging.save(uwlog);

			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			log.info("getUWDetails service failed,error:" + e.getMessage());
			uwlog.setError(e.getMessage());
			logging.save(uwlog);

			return new ResponseEntity<>(new UWException(new Date(), "Try Again", HttpStatus.LOCKED.toString()),
					HttpStatus.LOCKED);
		}

	}

}
