package com.github.maximovj.linuxhubapi.linuxhub_api.data.project;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Environment 
{
    NodeJS("Node JS"),
    Python("Python"),
    RubyOnRails("Ruby on rails"),
    Flask("Flask"),
    Django("Django"),
    Java("Java"),
    PHP("PHP");

    private final String value;

    Environment(String value) 
    {
        this.value = value;
    }

    @JsonValue
    public String getValue() 
    {
        return value;
    }

    @JsonCreator
    public static Environment fromValue(String value) {
        for (Environment status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

}
