package org.myrest.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SampleController {

	@RequestMapping("/hello")
	@ResponseBody
	String hello() {
		return "hello";
	}

	@RequestMapping("/bye")
	@ResponseBody
	String bye() {
		return "bye";
	}

	@RequestMapping("/helloName")
	@ResponseBody
	String helloName(String name) {
		return "Hello  " + name;
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleController.class, args);
	}
}
