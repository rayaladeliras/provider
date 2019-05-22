package com.stubhub.contract;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class PersonRestController {

	private final PersonService personService;

	public PersonRestController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/person/{id}")
	public Person findPersonById(@PathVariable("id") Long id) {
		return personService.findPersonById(id);
	}

    @GetMapping("/person")
    public List<Person> list() {
        return personService.returnAll();
    }
}
