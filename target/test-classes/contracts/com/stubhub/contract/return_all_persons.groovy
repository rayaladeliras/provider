package contracts.com.stubhub.contract

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "should return person by id=1"

	request {
		url "/person"
		method GET()
	}

	response {
		status OK()
		headers {
			contentType applicationJson()
		}
		body (file("return_all_persons.json"))
	}
}