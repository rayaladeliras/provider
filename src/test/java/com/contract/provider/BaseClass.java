package com.contract.provider;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EvenOddApplication.class)
public class BaseClass {

    @Before
    public void setup() {
       // StandaloneMockMvcBuilder standaloneMockMvcBuilder
       //         = MockMvcBuilders.standaloneSetup(evenOddController);
       // RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);

        RestAssuredMockMvc.standaloneSetup(new EvenOddController());
    }
}
