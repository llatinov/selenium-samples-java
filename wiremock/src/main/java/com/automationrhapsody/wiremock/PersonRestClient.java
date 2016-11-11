package com.automationrhapsody.wiremock;

import com.automationrhapsody.wiremock.model.Person;

import java.util.List;

public interface PersonRestClient {

    List<Person> getAll();

    Person get(int id);

    String save(Person person);

    String remove();
}
