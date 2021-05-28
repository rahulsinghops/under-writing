package com.ipru.mca.underwriting.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipru.mca.underwriting.dao.UnderWritingRepo;
import com.ipru.mca.underwriting.logging.LoggingRepo;
import com.ipru.mca.underwriting.model.BikeInput;
import com.ipru.mca.underwriting.model.BikeOutput;
import com.ipru.mca.underwriting.model.BreInputDetails;
import com.ipru.mca.underwriting.model.BreOutputDetails;
import com.ipru.mca.underwriting.model.UWInput;

@Service
public class UWService {

	@Autowired
	UnderWritingRepo repo;

	@Autowired
	LoggingRepo logging;
	@Autowired
	BikeopdeiaService bikeserv;

	@Autowired
	BreDetailService breserv;

	Logger log = LoggerFactory.getLogger(UWService.class);

	public BikeOutput getBikeDetails(UWInput input) {
		log.info("getBikeDetails service called,input:" + input);
		// Bre service call
		BreInputDetails breinput = new BreInputDetails();
		breinput.setAge(input.getAge());
		breinput.setLowerRange(input.getLowerRange());
		breinput.setUpperRange(input.getUpperRange());
		breinput.setOffRoadFlag(input.getOffRoadFlag());
		breinput.setRoad(input.getRoad());
		breinput.setSeaorhill(input.getSeaorhill());
		BreOutputDetails breoutput = breserv.getBreDetails(breinput);		

		if (breoutput != null && breoutput.getType() != null && !breoutput.getType().equals("")) {
			// Bikeopedia call
			BikeInput bikeinput = new BikeInput();
			bikeinput.setCity(input.getCity());
			bikeinput.setMaxvalue(input.getUpperRange());
			bikeinput.setMinvalue(input.getLowerRange());
			bikeinput.setType(breoutput.getType());

			BikeOutput bikeoutput = bikeserv.getBikeopediaDetails(bikeinput);

			log.info("getBikeDetails service executed,output:" + bikeoutput);
			return bikeoutput;
		} else {
			log.info("getBikeDetails service executed without output:");
			return null;
		}

	}

	/*
	 * //bre call service public BreOutputDetails getBreDetails(BreInputDetails
	 * input) { log.info("getBreDetails service called,input:"+ input); //db log
	 * UWLogging logdata= logging.save(new UWLogging("getBreDetails",
	 * input.toString(), null, null, new Date())); try { //rest call HttpHeaders
	 * header = new HttpHeaders();
	 * header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	 * //header.set("Content-Type", "application/json"); HttpEntity<BreInputDetails>
	 * httpentity = new HttpEntity<BreInputDetails>(input,header);
	 * //ResponseEntity<BreOutputDetails> entity=
	 * restTemplate.exchange("http://BRE-SERVICE/mca/bre",
	 * HttpMethod.POST,httpentity, BreOutputDetails.class, input);
	 * ResponseEntity<BreOutputDetails> entity=
	 * restTemplate.exchange("http://bre-service/mca/bre",
	 * HttpMethod.POST,httpentity, BreOutputDetails.class); //ResponseEntity<?>
	 * entity= restTemplate.getForEntity("http://BRE-SERVICE/mca/bre",
	 * BreOutputDetails.class, input);
	 * if(entity.getStatusCode().toString().equals("202 ACCEPTED")) {
	 * BreOutputDetails output=(BreOutputDetails) entity.getBody();
	 * log.info("getBreDetails service executed,output:"+output);
	 * logdata.setResponse(output.toString()); logging.save(logdata); return output;
	 * } else { log.info("getBreDetails service executed without success");
	 * logdata.setError("return status code:"+entity.getStatusCode().toString());
	 * logging.save(logdata); return new BreOutputDetails(); }} catch(Exception e) {
	 * log.info("getBreDetails service failed ,error:"+e.getMessage());
	 * logdata.setError(e.getMessage()); logging.save(logdata); return new
	 * BreOutputDetails(); }
	 * 
	 * }
	 */

	/*
	 * //Bikeopdeia call service public BikeOutput getBikeopediaDetails(BikeInput
	 * input) { log.info("getBikeopediaDetails service called,input:"+ input); //db
	 * log UWLogging logdata= logging.save(new UWLogging("getBikeopediaDetails",
	 * input.toString(), null, null, new Date())); try { //rest call HttpHeaders
	 * header = new HttpHeaders();
	 * header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	 * //header.set("Content-Type", "application/json"); HttpEntity<BikeInput>
	 * httpentity = new HttpEntity<BikeInput>(input,header);
	 * //ResponseEntity<BreOutputDetails> entity=
	 * restTemplate.exchange("http://BRE-SERVICE/mca/bre",
	 * HttpMethod.POST,httpentity, BreOutputDetails.class, input);
	 * ResponseEntity<BikeOutput> entity=
	 * restTemplate.exchange("http://bike-o-pedia/mca/bikes",
	 * HttpMethod.POST,httpentity, BikeOutput.class); //ResponseEntity<?> entity=
	 * restTemplate.getForEntity("http://localhost:8092/mca/bikes",
	 * BikeOutput.class, input);
	 * if(entity.getStatusCode().toString().equals("202 ACCEPTED")) { BikeOutput
	 * output=(BikeOutput) entity.getBody();
	 * log.info("getBikeopediaDetails service executed,output:"+output);
	 * logdata.setResponse(output.toString()); logging.save(logdata); return output;
	 * } else { log.info("getBikeopediaDetails service executed without success");
	 * return new BikeOutput(); }} catch(Exception e) {
	 * log.info("getBikeopediaDetails service failed ,error:"+e.getMessage());
	 * logdata.setError(e.getMessage()); logging.save(logdata); return new
	 * BikeOutput(); }
	 * 
	 * }
	 */
}
