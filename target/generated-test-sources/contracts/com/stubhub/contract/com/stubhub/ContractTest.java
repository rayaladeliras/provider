package com.stubhub.contract.com.stubhub;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.stubhub.contract.BaseClass;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;

public class ContractTest extends BaseClass {

	@Test
	public void validate_find_person_by_id() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();

		// when:
			ResponseOptions response = given().spec(request)
					.get("/person/1");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['name']").isEqualTo("Richard");
			assertThatJson(parsedJson).field("['surname']").isEqualTo("Gere");
			assertThatJson(parsedJson).field("['id']").isEqualTo(1);
	}

	@Test
	public void validate_return_all_persons() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();

		// when:
			ResponseOptions response = given().spec(request)
					.get("/person");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).array().contains("['id']").isEqualTo(1);
			assertThatJson(parsedJson).array().contains("['name']").isEqualTo("Anna");
			assertThatJson(parsedJson).array().contains("['name']").isEqualTo("Richard");
			assertThatJson(parsedJson).array().contains("['surname']").isEqualTo("Gere");
			assertThatJson(parsedJson).array().contains("['surname']").isEqualTo("Choplin");
			assertThatJson(parsedJson).array().contains("['surname']").isEqualTo("Carolina");
			assertThatJson(parsedJson).array().contains("['id']").isEqualTo(3);
			assertThatJson(parsedJson).array().contains("['id']").isEqualTo(2);
			assertThatJson(parsedJson).array().contains("['name']").isEqualTo("Emma");
	}

}
