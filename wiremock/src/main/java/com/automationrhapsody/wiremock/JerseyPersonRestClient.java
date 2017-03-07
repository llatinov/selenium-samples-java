package com.automationrhapsody.wiremock;

import com.automationrhapsody.wiremock.model.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.filter.LoggingFilter;

public class JerseyPersonRestClient implements PersonRestClient {

    private static final String ENDPOINT_GET_ALL = "/person/all";
    private static final String ENDPOINT_GET_BY_ID = "/person/get/";
    private static final String ENDPOINT_SAVE = "/person/save";
    private static final String ENDPOINT_REMOVE = "/person/remove";

    private final WebTarget webTarget;

    public JerseyPersonRestClient(String host) {
        ClientConfig clientConfig = new ClientConfig()
            .property(ClientProperties.READ_TIMEOUT, 30000)
            .property(ClientProperties.CONNECT_TIMEOUT, 5000);

        webTarget = ClientBuilder
            .newClient(clientConfig)
            .register(new LoggingFilter())
            .target(host);
    }

    @Override
    public List<Person> getAll() {
        Person[] persons = webTarget
            .path(ENDPOINT_GET_ALL)
            .request()
            .get()
            .readEntity(Person[].class);
        return Arrays.stream(persons).collect(Collectors.toList());
    }

    @Override
    public Person get(int id) {
        return webTarget
            .path(ENDPOINT_GET_BY_ID + id)
            .request()
            .get()
            .readEntity(Person.class);
    }

    @Override
    public String save(Person person) {
        if (person == null) {
            throw new RuntimeException("Person entity should not be null");
        }
        return webTarget
            .path(ENDPOINT_SAVE)
            .request()
            .post(Entity.json(person))
            .readEntity(String.class);
    }

    @Override
    public String remove() {
        return webTarget
            .path(ENDPOINT_REMOVE)
            .request()
            .get()
            .readEntity(String.class);
    }
}
