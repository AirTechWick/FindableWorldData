package com.foothill;

import java.io.IOException;

public interface Findable {
    double NO_DATA = -1.0;
    int AREA = 1;
    int BIRTH_RATE = 2;
    int DEATH_RATE = 3;
    int GDP = 4;
    int HIV_DEATHS = 5;
    int INFANT_MORTALITY = 6;
    int LABOR_FORCE = 7;
    int LIFE_EXPECTANCY = 8;
    int POPULATION = 9;
    int MOBILE_PHONES = 10;
    int FERTILITY_RATE = 11;
    int UNEMPLOYMENT = 12;

    double findFact(int attribute) throws IOException;
    double findEnrollmentByYear(int year) throws IOException;
}