package com.automationrhapsody.wiremock;

public class PersonRestClientBuilder {

    private String host;

    public PersonRestClientBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public PersonRestClient build() {
        return new JerseyPersonRestClient(host);
    }
}
