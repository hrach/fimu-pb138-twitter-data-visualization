package com.skrasek.school.pb138;

import java.util.*;

/**
 * 
 * Class is creating complete URL address to generate chart using Google Chart service.
 * @author tomasbobek
 */
public class ChartUrl {
    
    private Map<String,List<Integer>> trends = new HashMap<String,List<Integer>>();
    
    /**
     * Constructor, taking Map collection contained of String key (as trend name) and List of integers
     * (as daily popularity values) as parameter
     * @param trends 
     */
    public ChartUrl(Map<String,List<Integer>> trends) {
        this.trends.putAll(trends);
    }
    
    private String createURL() {
        String names = "";
        String values = "";
        String days = "";
        String points = "";
        String url = "https://chart.googleapis.com/chart?cht=lc&chs=480x320&chxt=x,y&chco=3072F3,ff0000,00aaaa";
        
        int maxday = 0;
        int maxval = 100;
                
        for (Map.Entry<String, List<Integer>> entry : this.trends.entrySet()) {
            names += entry.getKey() + "|";
            if(entry.getValue().size() > maxday) maxday = entry.getValue().size();
            
            for (Integer i : entry.getValue()) {
                values += i.toString() + ",";
                //if (i>maxval) maxval = i;
            }
            
            values = values.substring(0, values.length() - 1);
            values += "|";
        }
        
        names = names.substring(0, names.length() - 1);
        values = values.substring(0, values.length() - 1);
        
        for (int i=1; i<=maxday; i++) {
            days += "|" + i;
        }
        
        for (int i=0; i<=maxval; i=i+(maxval/10)) {
            points += "||" + i;
        }
        
        url += "&chd=t:" + values;
        url += "&chxl=0:" + days + "|1:" + points;
        url += "&chdl=" + names;
        return url;
    }
    
    @Override
    public String toString() {
        return this.createURL();
    }
}
