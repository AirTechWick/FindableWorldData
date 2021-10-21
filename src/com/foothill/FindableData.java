package com.foothill;
// Project: com.foothill.FindableData
// TODO 1
// Author: Erik Rodriguez
//

import com.foothill.CountryData;
import com.foothill.ImportException;
import com.foothill.WorldData;

import java.io.IOException;

public class FindableData {

    public static void showData(double data) {
        System.out.println((data == CountryData.NO_DATA) ? "no data available" : data);
    }

    public static void main(String[] args) throws IOException {
        WorldData wd = new WorldData();
        CountryData c;
        wd.loadEnrollmentData();
        try {
            wd.importFactBookData();
        }
        catch (ImportException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("-----------------------------------");
        c = wd.lookup("DJI");
        System.out.print("Djibouti's Area: ");
        showData(c.findFact(CountryData.AREA));
        c = wd.lookup("YEM");
        System.out.print("Yemen's Birth Rate: ");
        showData(c.findFact(CountryData.BIRTH_RATE));

        // TODO 2
        // add same queries for the other 10 attributes
        c = wd.lookup("USA");
        System.out.println("United State's Death Rate: ");
        showData(c.findFact(CountryData.DEATH_RATE));
        System.out.println("United State's GDP Rate: ");
        showData(c.findFact(CountryData.GDP));
        System.out.println("United State's HIV_DEATHS: ");
        showData(c.findFact(CountryData.HIV_DEATHS));
        System.out.println("United State's INFANT_MORTALITY: ");
        showData(c.findFact(CountryData.INFANT_MORTALITY));
        System.out.println("United State's LABOR_FORCE: ");
        showData(c.findFact(CountryData.LABOR_FORCE));
        System.out.println("United State's LIFE_EXPECTANCY: ");
        showData(c.findFact(CountryData.LIFE_EXPECTANCY));
        System.out.println("United State's POPULATION: ");
        showData(c.findFact(CountryData.POPULATION));
        System.out.println("United State's MOBILE_PHONES: ");
        showData(c.findFact(CountryData.MOBILE_PHONES));
        System.out.println("United State's FERTILITY_RATE: ");
        showData(c.findFact(CountryData.FERTILITY_RATE));
        System.out.println("United State's UNEMPLOYMENT: ");
        showData(c.findFact(CountryData.UNEMPLOYMENT));

        c = wd.lookup("MNA");
        System.out.println("Middle East / North Africa");
        System.out.print("enrollment (1960): ");
        showData(c.findEnrollmentByYear(1960));
        System.out.print("enrollment (1970): ");
        showData(c.findEnrollmentByYear(1970));

        // TODO 3
        // add one more enrollment by year query
        System.out.print("enrollment (2015): ");
        showData(c.findEnrollmentByYear(2015));

        // TODO 4
        // add try/catch with a call to findFact() with an invalid argument
        int number = 22;
        try{
            c.findFact(number);
        }
        catch (IOException e) {
            System.out.println("Invalid Argument<"+number+">");
        }


        // TODO 5
        // add try/catch with a call to findEnrollmentByYear() with an invalid argument
        number = 55;

        try{
            c.findEnrollmentByYear(number);
        }
        catch (IOException e) {
            System.out.println("Invalid Argument<"+number+">");
        }

        System.out.println("-----------------------------------");
    }
}

// TODO 6
/*

<84> non-matching or failed imports
-----------------------------------
Djibouti's Area: 23000.0
Yemen's Birth Rate: 0.0
United State's Death Rate:
8.25
United State's GDP Rate:
1.175E13
United State's HIV_DEATHS:
14000.0
United State's INFANT_MORTALITY:
6.5
United State's LABOR_FORCE:
1.474E8
United State's LIFE_EXPECTANCY:
77.71
United State's POPULATION:
2.95734134E8
United State's MOBILE_PHONES:
1.58722E8
United State's FERTILITY_RATE:
2.08
United State's UNEMPLOYMENT:
no data available
Middle East / North Africa
enrollment (1960): 0.0
enrollment (1970): 21.34574
enrollment (2015): 68.61788
Invalid Argument<22>
Invalid Argument<55>
-----------------------------------

*/
