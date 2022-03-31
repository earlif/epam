package com.epam.liegou.projects.demo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.liegou.framework.utils.Utils.*;

public class hello {

    private static final Logger logger = LoggerFactory.getLogger(hello.class);

    @Given("I say hello")
    public void iSayHello(){
        logger.info("IF");
        System.out.println("hello, LieGou!");
    }

    @When("reply hello")
    public void replyHello() {
        System.out.println("Hello, IF");
        logPass("test");
    }
}
