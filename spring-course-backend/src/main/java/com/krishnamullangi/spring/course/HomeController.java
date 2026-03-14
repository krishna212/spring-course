package com.krishnamullangi.spring.course;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	// Simple test route. Open localhost:8080 to see this text.
	public String home() {
		return "Spring Course App is Running!";
	}

}
 