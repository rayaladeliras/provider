package com.stubhub.contract;

import com.stubhub.contract.ContractRestServiceApplication;
import com.stubhub.contract.Person;
import com.stubhub.contract.PersonRestController;
import com.stubhub.contract.PersonService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContractRestServiceApplication.class)
public abstract class BaseClass {

	@Autowired
	PersonRestController personRestController;

	@MockBean
	PersonService personService;

    private final Map<Long, Person> personMap = new HashMap<>();
    private final ArrayList<Person> personList = new ArrayList<Person>();

	@Before public void setup() {
		RestAssuredMockMvc.standaloneSetup(personRestController);
        initPersonMao();
		Mockito.when(personService.findPersonById(1L))
				.thenReturn(personMap.get(1L));

        Mockito.when(personService.returnAll())
                .thenReturn(personList);
	}

	private void initPersonMao() {
        personMap.put(1L, new Person(1L, "Richard", "Gere"));
        personMap.put(2L, new Person(2L, "Emma", "Choplin"));
        personMap.put(3L, new Person(3L, "Anna", "Carolina"));
        personList.addAll(personMap.values());
    }

}
