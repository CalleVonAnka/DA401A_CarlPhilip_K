package com.example.callevonanka.assignment_1;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Calle Von Anka on 2015-09-10.
 */
public class Helpers {

    public static String getDate(){
        String currentDate = DateFormat.getDateInstance().format(new Date());
        return currentDate;
    }

    public static String getQuote(String[] quoteArray){
        Random rand = new Random();
        int n = rand.nextInt(quoteArray.length);
        return quoteArray[n];
    }

}
