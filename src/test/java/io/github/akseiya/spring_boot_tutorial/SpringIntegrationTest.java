package io.github.akseiya.spring_boot_tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.*;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.akseiya.spring_boot_tutorial.model.Fibo;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {
  @Autowired
  private TestRestTemplate app;

  private ArrayList<Integer> positions = new ArrayList<Integer>();
  private int position;

  private long getFiboValue(int position) {
    ResponseEntity<Fibo> response = app.getForEntity(
      String.format("/fibo?position=%d", position), 
      Fibo.class
    );
    HttpStatusCode status = response.getStatusCode();
    assertFalse(status.isError());
    Fibo wrapped = response.getBody();

    return wrapped.number();
  }

  @When("I ask for 3rd or later Fibonacci number")
  public void i_ask_for_3rd_or_later_fibonacci_number() throws Throwable{
    assertTrue(getFiboValue(3) == 1);
    positions.addLast((int)3);
    for(int i = 0; i < 10; i++)
      positions.addLast((int)(Math.random() * 50 + 4));
  }
  
  @When("I ask for {word} Fibonacci number")
  public void I_Ask_For_Nth_Fibo_Num(String which) throws Throwable {
    switch(which) {
      case "1st": position = 1; break;
      case "2nd": position = 2; break;
      default:
        throw new Exception("Unknown sequence position specification: " + which);
    }
  }

  @Then("I get the predefined number {long}")
  public void I_get_the_predefined_number(long expectedValue) {
    assertEquals(expectedValue, getFiboValue(position));
  }

  @Then("I always get the sum of its two predecessors")
  public void I_get_the_sum_of_its_two_predecessors() {
    positions.forEach((n) -> {
      long expected = getFiboValue(n - 1) + getFiboValue(n - 2);
      long actual = getFiboValue(n);
      assertEquals(expected, actual);
    });
  }
}
