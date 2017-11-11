package ohtu;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import gherkin.lexer.Th;
import ohtu.io.*;
import ohtu.data_access.*;
import ohtu.services.*;

public class Stepdefs {
    Counter counter;


    @Given("^Counter is initialized$")
    public void counter_is_initialized() throws Throwable {
        counter = new Counter();
    }

    @When("^it is incremented$")
    public void it_is_incremented() throws Throwable {
        counter.increase();
    }

    @When("^it is incremented by (\\d+)$")
    public void it_is_incremented_by(int val) throws Throwable {
        counter.increment(val);
    }


    @And("^it is reseted$")
    public void it_is_reseted() throws Throwable {
        counter.reset();
    }

    @Then("^the value should be (\\d+)$")
    public void the_value_should_be(int val) throws Throwable {
        assertEquals(val, counter.value());
    }


}
