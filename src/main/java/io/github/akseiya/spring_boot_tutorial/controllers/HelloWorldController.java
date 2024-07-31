package io.github.akseiya.spring_boot_tutorial.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}  
}
