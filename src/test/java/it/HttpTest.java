package it;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.verify.VerificationTimes;

public class HttpTest {
	@Rule
	public MockServerRule mockServerRule = new MockServerRule(this);

	private MockServerClient mockServerClient;

	@Test
	public void shouldConnectToHttpService() throws Exception {
		// setting behaviour for test case
		mockServerClient.when(HttpRequest.request("/us/en/foo.extension")).respond(HttpResponse.response().withStatusCode(200));

		// create a GET request using JAX-RS rest client API
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:" + mockServerRule.getPort()).path("/us/en/foo.extension").request().get();

		// assert response
		assertThat(response.getStatus(), equalTo(200));

		// verify server has received exactly one request
		mockServerClient.verify(HttpRequest.request("/us/en/foo.extension"), VerificationTimes.once());
	}

	@Test
	public void shouldConnectToAnotherHttpService() throws Exception {
		// setting behaviour for test case
		mockServerClient.when(HttpRequest.request("/de/de/foo.extension")).respond(HttpResponse.response().withStatusCode(200));

		// create a GET request using JAX-RS rest client API
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:" + mockServerRule.getPort()).path("/de/de/foo.extension").request().get();

		// assert response
		assertThat(response.getStatus(), equalTo(200));

		// verify server has received exactly one request
		mockServerClient.verify(HttpRequest.request("/de/de/foo.extension"), VerificationTimes.once());
	}
}
