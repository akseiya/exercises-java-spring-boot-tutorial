package io.github.akseiya.spring_boot_tutorial.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.akseiya.spring_boot_tutorial.model.Fibo;

@RestController
public class FiboController {
  @GetMapping("/fibo")
  public Fibo fibo(@RequestParam(required = true) int position) throws Throwable {
    long last = 1;
    long prev = 0;
    if(position < 1) throw new Exception("The sequence starts at position 1");
    if(position == 1) return new Fibo(0);
    if(position == 2) return new Fibo(1);
    for(int newMaxPos = 2; newMaxPos < position; newMaxPos++) {
      long new_last = last + prev;
      prev = last;
      last = new_last;
    }
    return new Fibo(last);
  }
}
