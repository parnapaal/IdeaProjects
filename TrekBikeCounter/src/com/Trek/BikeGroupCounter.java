package com.Trek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BikeGroupCounter {
    public Map<String, Integer> bikeCounter;
    public String[] top20;
    public Integer[] top20Numbers;

    //instantiate the counter
    public BikeGroupCounter() {
        //instantiate the main class -- this is where all the work is taking place

        //this Map holds all the bike combinations and the number of times they are seen
        bikeCounter = new HashMap<>();

        String url = "https://trekhiringassignments.blob.core.windows.net/interview/bikes.json";

        //download all data from the url to a string
        String content = readFromInternet(url);

        //parse and add up all the different bike combinations
        parseAndTally(content);

        //populated top20 and top20 numbers with the top 20 bike combos and the number of times they are seen
        returnTop20();


    }




    //take in a url and pull the needed data into a String
    public String readFromInternet(String urlString){
        StringBuilder content = new StringBuilder();

        try
        {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return content.toString();
    }


    //using a few different classes, parse this info into a form we can best work with it,
    //tally up all the combinations we see,
    //and removed all instances within our list that are just a single bike, not a combination
    //of bikes
    public void parseAndTally(String content){
        String[] parsedBikes = parseByBike(content);
        for(int i = 1; i < parsedBikes.length; i++){
            parsedBikes[i] = clean(parsedBikes[i]);
            parsedBikes[i] = alphabetize(parsedBikes[i]);
        }

        parsedBikes = Arrays.copyOfRange(parsedBikes, 1, parsedBikes.length);
        bikeCounter = tally(parsedBikes);

    }

    //if a key is not seen within a hashmap before with a quantity of '0', add it to the hashmap,
    // then increment the value of the HashMap entity by 1
    public Map<String, Integer> tally(String[] bikes){
        Map<String, Integer> frequencies = new HashMap<>();

        for(String bike : bikes) {
            if(!frequencies.containsKey(bike)){
                frequencies.put(bike,0);
            }
            frequencies.put(bike, frequencies.get(bike)+1);
        }
        return frequencies;
    }

    //split up our bike list on the word 'bikes'
    public String[] parseByBike(String content){

        String[] bikes = content.split("bikes");
        return bikes;
    }


    //clean out extraneous spaces and symbols here
    public String clean(String toClean){
        toClean = toClean.replaceAll("[^a-zA-Z0-9.+]", " ");
        toClean = toClean.strip();
        return toClean;
    }

    //alphabetize our bike combinations so that out of order sequences are not counted as
    //unique combinations. We will also get rid of lists that contain a single bike here.
    public String alphabetize(String outOfOrder){
        outOfOrder = outOfOrder.replace("\t", "");
        String[] outOfOrderToArray = outOfOrder.split("    ");
        Arrays.sort(outOfOrderToArray);

        //if our array length is one, we do not have a combination of bikes. Therefore, we will
        //replace this instance within our array with 'null' as we do not care about
        //households without a combination of bikes in this instance
        if(outOfOrderToArray.length == 1){
            return null;
        }

        String sorted = clean(Arrays.toString(outOfOrderToArray));
        return sorted;
    }

    //instantiate our top20 arrays. Run through our Hashmap list 21 times, finding the maximum value
    //placing it into our arrays, then removing it from our map so we can find the next largest
    //value. We iterate through this list 21 times to account for the null values at the beginning
    //of the list that were created by replacing all non-combination bike households with 'null'
    public void returnTop20(){
        top20 = new String[21];
        top20Numbers = new Integer[21];

        for(int i=0; i<21; i++){
            String maximum = maxForIteration();
            top20[i] = maximum;
            top20Numbers[i] = bikeCounter.get(maximum);
            bikeCounter.remove(maximum);
        }
    }

    // iterate through our HashMap once and return the key related to the highest value within our map
    public String maxForIteration(){
        int MaxSoFar = 0;
        String MaxKey = "";
        for(Map.Entry<String, Integer> e : bikeCounter.entrySet()){
            if(e.getValue() > MaxSoFar) {
                MaxSoFar = e.getValue();
                MaxKey = e.getKey();
            }

        }
        return MaxKey;
    }

    //print out our top 20 most common bike combinations
    public void printBikeCombos(){
        System.out.println("Top 20 bike combinations: \n");
        for(int i = 1; i<21; i++) {
            System.out.println("#"+ (i) + " most common combination : " + top20[i] + ": " + top20Numbers[i]
                    + " families own these bikes" + "\n");
        }

    }
    
    public static void main(String[] args) {
        BikeGroupCounter counter = new BikeGroupCounter();
        counter.printBikeCombos();
    }


}
