package com.foothill;

import java.io.IOException;
import java.util.Arrays;

// The purpose of this class is to create and store each countries data
public class CountryData extends Country implements Findable {
    // variables
    private double enrollment[];
    private double facts[];

    // methods
    CountryData() {
        super("-", "-");
    }

    CountryData(String code, String name) {
        super(code, name);
        facts = new double[45];
    }

    public double[] getEnrollment() {
        return enrollment;
    }

    public double[] getFacts() {
        return facts;
    }

    public void setEnrollment(double[] enrollment) {
        this.enrollment = enrollment;
    }

    public void setFacts(double[] facts) {
        this.facts = facts;
    }

    // uses the attributes list in the findable interface to search for the correct attribute in the facts array
    public double findFact(int attribute) throws IOException {
        switch (attribute) {
            case Findable.AREA:
                return facts[1];
            case Findable.BIRTH_RATE:
                return facts[2];
            case Findable.DEATH_RATE:
                return facts[4];
            case Findable.GDP:
                return facts[9];
            case Findable.HIV_DEATHS:
                return facts[13];
            case Findable.INFANT_MORTALITY:
                return facts[18];
            case Findable.LABOR_FORCE:
                return facts[23];
            case Findable.LIFE_EXPECTANCY:
                return facts[24];
            case Findable.POPULATION:
                return facts[37];
            case Findable.MOBILE_PHONES:
                return facts[42];
            case Findable.FERTILITY_RATE:
                return facts[43];
            case Findable.UNEMPLOYMENT:
                return facts[44];
            default: {
                throw new IOException("Bad attribute provided <" + attribute + ">");
            }
        }


    }

    public double findEnrollmentByYear(int year) throws IOException {
        final int STARTING_YEAR = 1960;
        final int ENDING_YEAR = 2019;
        final int ENROLLMENT_INDEX = (year - STARTING_YEAR) + 2;

        if (year < STARTING_YEAR || year > ENDING_YEAR)
        {
            throw new IOException("Bad year provided <" + year + ">");
        }

        return enrollment[ENROLLMENT_INDEX];
    }

    public String toString() {

        String strArray = Arrays.toString(enrollment);
        strArray = strArray.replaceAll("0.0", "-");

        return "CountryData{" +
                "code='" + getCode() + "', " +
                "name='" + getName() + "', " +
                "enrollment=" + strArray + ", " +
                "facts=" + Arrays.toString(getFacts()) +
                '}';
    }
}