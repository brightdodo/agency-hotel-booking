package com.hotel.agency.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AgencyHotelBookingApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
        new AgencyHotelBookingApplication().configure(new SpringApplicationBuilder(AgencyHotelBookingApplication.class)).run(args);
//		SpringApplication.run(AgencyHotelBookingApplication.class, args);
	}

}
