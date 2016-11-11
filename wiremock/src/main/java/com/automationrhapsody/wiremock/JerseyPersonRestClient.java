package com.automationrhapsody.wiremock;

import com.automationrhapsody.wiremock.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
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
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

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
        String response = webTarget
                .path(ENDPOINT_GET_ALL)
                .request()
                .get()
                .readEntity(String.class);
        Person[] persons = readValue(response, Person[].class);
        return Arrays.stream(persons).collect(Collectors.toList());
    }

    @Override
    public Person get(int id) {
        String response = webTarget
                .path(ENDPOINT_GET_BY_ID + id)
                .request()
                .get()
                .readEntity(String.class);
        return readValue(response, Person.class);
    }

    @Override
    public String save(Person person) {
        try {
            if (person != null) {
                return webTarget
                        .path(ENDPOINT_SAVE)
                        .request()
                        .post(Entity.json(OBJECT_MAPPER.writeValueAsString(person)))
                        .readEntity(String.class);
            }
        } catch (JsonProcessingException e) {
            // TODO Exception handling goes here
        }
        return null;
    }

    @Override
    public String remove() {
        return webTarget
                .path(ENDPOINT_REMOVE)
                .request()
                .get()
                .readEntity(String.class);
    }

    private <T> T readValue(String content, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(content, clazz);
        } catch (IOException e) {
            // TODO Exception handling goes here
        }
        return null;
    }
}
