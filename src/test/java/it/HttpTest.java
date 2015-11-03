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
	public MockServerRule mockServerRule = new MockServerRule(this, 9000);

	private MockServerClient mockServerClient;

	@Test
	public void shouldConnectToHttpService() throws Exception {
		// setting behaviour for test case
		mockServerClient.when(HttpRequest.request("/foo")).respond(HttpResponse.response().withStatusCode(200));

		// create a GET request using JAX-RS rest client API
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:9000").path("/foo").request().get();

		// assert response
		assertThat(response.getStatus(), equalTo(200));

		// verify server has received exactly one request
		mockServerClient.verify(HttpRequest.request("/foo"), VerificationTimes.once());
	}
}
