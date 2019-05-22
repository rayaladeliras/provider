package com.contract.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvenOddController {

    @GetMapping("/validate/even-odd")
    public String isEvenOrOdd(@RequestParam("number") Integer number) {
        return number % 2 == 0 ? "EVEN" : "ODD";
    }
}