package com.foothill;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;


// The purpose of this class is to create a list of all the countries in the World and display their data
public class WorldData {
    // this is for loading the enrollment file
    private CountryData countryData[];
    private static final int ATTRIBUTE_COUNT = 63;
    private int count = 0;

    // this is for loading the factbook file
    static final int COUNTRIES = 263;
    static final int ATTRIBUTES = 45;
    private String[] countries = new String[COUNTRIES];
    private double[][] attributes = new double[COUNTRIES][ATTRIBUTES];

    // methods
    WorldData() {
        this(new CountryData[500]);
    }

    WorldData(CountryData[] countryData) {
        this.countryData = countryData;
    }

    // adds a country into an index that is null
    public void addCountry(CountryData country) {
        int index = 0;
        while (true) {
            if (countryData[index] == null) {
                countryData[index] = country;
                count++;
                break;
            }
            index++;
        }
    }

    public int getCount() {
        return count;
    }

    // loads enrollment data from the csv file given
    public void loadEnrollmentData() throws FileNotFoundException, ImportException {
        File enrollmentFile = new File("src/com/foothill/API_SE.SEC.NENR_DS2_en_csv_v2_1347167.csv");
        Scanner scnr = new Scanner(enrollmentFile);
        scnr.useDelimiter(",|\n");
        scnr.nextLine();
        scnr.nextLine();
        scnr.nextLine();


        int index = 0;
        String stringArray[] = new String[ATTRIBUTE_COUNT];
        double doubleArray[] = new double[ATTRIBUTE_COUNT];


        while (scnr.hasNext()) {
            String currentToken = scnr.next();
            int len = currentToken.length();


            if (currentToken.charAt(0) == '"') {
                currentToken = currentToken.substring(1, len - 1);
            }

            stringArray[index] = currentToken;

            index++;

            if (index == ATTRIBUTE_COUNT) {

                // loading current country attributes into one country
                for (int i = 0; i < ATTRIBUTE_COUNT; i++) {
                    String currentItem = stringArray[i];
                    boolean isDigit = false;

                    if (currentItem != "") {
                        char firstLetter = currentItem.charAt(0);
                        isDigit = Character.isDigit(firstLetter);
                    }

                    if (isDigit) {
                        double dblItem = Double.parseDouble(currentItem);
                        doubleArray[i] = dblItem;
                    } else {
                        doubleArray[i] = 0;
                    }
                }
                String name = stringArray[0];
                String code = stringArray[1];
                CountryData currentCountryData = new CountryData(code, name);
                addCountry(currentCountryData);
                currentCountryData.setEnrollment(doubleArray);
                doubleArray = new double[ATTRIBUTE_COUNT];
                index = 0;
            }
        }
    }

    // imports the fact book data and inserts it into the countryData array
    public void importFactBookData() throws FileNotFoundException, ImportException {
        loadFactBookData();
        int failedImports = 0;
        for (int i = 0; i < COUNTRIES; i++) {
            CountryData lookupResult = lookup(countries[i]);
            if (lookupResult != null) {
                // set area, population, gdp, infantMortality, hardcoded the correct column they are in the csv file

            } else {
                failedImports++;
            }
        }
        if (failedImports > 0) {
            throw new ImportException(failedImports);
        }
    }

    // loads the file factbook.csv and inserts the data into a 2D array
    public void loadFactBookData() throws FileNotFoundException {
        File FactBookFile = new File("src/com/foothill/factbook.csv");

        Scanner scnr = new Scanner(FactBookFile);
        scnr.useDelimiter(";|\n");
        // Skips first two lines
        scnr.nextLine();
        scnr.nextLine();

        // loads data into local arrays
        while (scnr.hasNext()) {
            for (int i = 0; i < COUNTRIES; i++) {
                double facts[] = new double[ATTRIBUTES];
                for (int n = 0; n < ATTRIBUTES; n++) {
                    if (scnr.hasNextDouble()) {
                        double currentToken = scnr.nextDouble();
                        attributes[i][n] = currentToken;
                        facts[n] = currentToken;
                    }
                    else {
                        String currentToken = scnr.next();
                        if (countries[i] == null)
                            countries[i] = currentToken;

                        facts[n] = Findable.NO_DATA;
                        attributes[i][n] = Findable.NO_DATA; // NO_DATA is -1.0
                    }
                }
                CountryData result = lookup(countries[i]);
                if (result != null)
                    result.setFacts(facts);
            }
        }
        scnr.close();
    }

    // looks up a given code or name in the current world data-set
    public CountryData lookup(String codeOrName) {
        for (int i = 0; i < countryData.length; i++) {
            CountryData currdata = countryData[i];
            if (currdata == null) {
                break;
            }
            if (codeOrName.equals(currdata.getCode()) || codeOrName.equals(currdata.getName())) {
                return currdata;
            }
        }
        return null;
    }


    public String toString() {
        CountryData[] countryDataNoNull = new CountryData[count];
        int index = 0;

        while (true) {
            if (countryData[index] == null) {
                break;
            }
            countryDataNoNull[index] = countryData[index];
            index++;
        }

        return "WorldData{" +
                "countries=" + countryDataNoNull.toString() +
                '}';
    }
}