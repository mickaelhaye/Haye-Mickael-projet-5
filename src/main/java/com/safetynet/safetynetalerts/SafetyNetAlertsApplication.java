package com.safetynet.safetynetalerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.safetynetalerts.controller.Controller;

import lombok.Data;

@Data
@SpringBootApplication
public class SafetyNetAlertsApplication {

	@Autowired
	private Controller controller;

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

}
