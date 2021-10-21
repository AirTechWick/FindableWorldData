package com.foothill;

// The purpose of this class is to create a template for what a country object is
public abstract class Country {
    // variables
    private String code;
    private String name;

    // methods
    Country() {
        this("-", "-");
    }

    Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public abstract String toString();
}