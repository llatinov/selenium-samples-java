package com.automationrhapsody.wiremock;

import com.automationrhapsody.wiremock.model.Person;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.github.tomakehurst.wiremock.matching.UrlPattern;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.HttpHeaders;

import org.apache.http.entity.ContentType;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JerseyPersonRestClientTest {

    private static final int WIREMOCK_PORT = 9999;
    private static final String PERSON_STRING = "{\"firstName\":\"FN1\",\"lastName\":\"LN1\"}";
    private static final UrlPattern GET_URL = urlMatching(".*/person/get/.*");

    @ClassRule
    public static final WireMockClassRule WIREMOCK_RULE = new WireMockClassRule(WIREMOCK_PORT);

    private JerseyPersonRestClient clientUnderTest;

    @Before
    public void setUp() throws Exception {
        clientUnderTest = new JerseyPersonRestClient("http://localhost:" + WIREMOCK_PORT);
    }

    @Test
    public void testGet_WithBody_PersonJson() {
        stubFor(get(GET_URL).willReturn(aResponse()
            .withHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
            .withBody(PERSON_STRING)));

        Person actual = clientUnderTest.get(1);

        assertEquals("FN1", actual.getFirstName());
        assertEquals("LN1", actual.getLastName());
    }

    @Test
    public void testGet_WithBody_EmptyJson() {
        stubFor(get(GET_URL).willReturn(aResponse()
            .withHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
            .withBody("{}")));

        Person actual = clientUnderTest.get(1);

        assertNull(actual.getFirstName());
        assertNull(actual.getLastName());
    }

    @Test(expected = ProcessingException.class)
    public void testGet_WithBody_InvalidJson() {
        stubFor(get(GET_URL).willReturn(aResponse()
            .withHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
            .withBody("invalid Json")));

        clientUnderTest.get(1);
    }

    @Test(expected = ProcessingException.class)
    public void testGet_WithFault() {
        stubFor(get(GET_URL).willReturn(aResponse()
            .withHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
            .withFault(Fault.EMPTY_RESPONSE)));

        clientUnderTest.get(1);
    }

    @Test
    public void testGet_WithStatus() {
        stubFor(get(GET_URL).willReturn(aResponse()
            .withHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
            .withStatus(500).withBody(PERSON_STRING)));

        Person actual = clientUnderTest.get(1);

        assertEquals("FN1", actual.getFirstName());
        assertEquals("LN1", actual.getLastName());
    }
}
