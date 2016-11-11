package com.automationrhapsody.wiremock;

import com.automationrhapsody.wiremock.model.Person;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        PersonRestClient client = new PersonRestClientBuilder()
                .setHost("http://localhost:9000")
                .build();

        List<Person> persons = client.getAll();
        System.out.println(persons.size() + ": " + persons);

        Person person = client.get(1);
        System.out.println(person);

        String save = client.save(person);
        System.out.println(save);

        String remove = client.remove();
        System.out.println(remove);

        System.out.println(client.getAll().size());
    }
}
