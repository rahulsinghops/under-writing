package com.ipru.mca.underwriting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.ipru.mca.underwriting.model.BreInputDetails;
import com.ipru.mca.underwriting.model.BreOutputDetails;
import com.ipru.mca.underwriting.service.UWService;

@SpringBootApplication
@EnableEurekaClient
public class UnderWritingApplication implements CommandLineRunner{
	
	@Autowired
	UWService service ;
	
@Bean
@LoadBalanced
public RestTemplate getRestTemplate()
{
return new RestTemplate();	
}
	
	public static void main(String[] args) {
		SpringApplication.run(UnderWritingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
 BreOutputDetails output =service.getBreDetails(new BreInputDetails(25, 76000, 176000, "Street", "Y", "SEA"))	;
 System.out.println(output);
	}

}
