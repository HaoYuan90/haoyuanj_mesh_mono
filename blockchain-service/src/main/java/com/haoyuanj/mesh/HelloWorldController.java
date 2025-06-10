package com.haoyuanj.mesh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

  @GetMapping("/")
  public String index() {
    logger.info("Greetings endpoint hit");
    return "Greetings from Spring Boot!";
  }
}
