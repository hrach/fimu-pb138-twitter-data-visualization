package com.skrasek.school.pb138;

import java.util.*;

/**
 * 
 * Class is creating complete URL address to generate chart using Google Chart service.
 * @author tomasbobek
 */
public class ChartUrl {
    
    private Map<String,List<Integer>> trends = new HashMap<String,List<Integer>>();
    private List<Date> dayList;
    
    /**
     * Constructor, taking Map collection contained of String key (as trend name) and List of integers
     * (as daily popularity values) as parameter
     * @param trends 
     */
    public ChartUrl(Map<String,List<Integer>> trends, List<Date> dayList) {
        this.trends.putAll(trends);
        this.dayList = dayList;
    }
    
    private String createURL() {
        String names = "";
        String values = "";
        String days = "";
        String points = "";
        String url = "https://chart.googleapis.com/chart?cht=lc&chs=480x320&chxt=x,y&chco=c00000,005cda,0ada00,a400da,f09800,00e0ce,e8e500,78bcfc,f69371,91ec8f";
        
        int maxval = 0;
        int graphSize = 320;

        for (Map.Entry<String, List<Integer>> entry : this.trends.entrySet()) {
            for (Integer i : entry.getValue()) {
                if (i > maxval) maxval = i;
            }
        }

        for (Map.Entry<String, List<Integer>> entry : this.trends.entrySet()) {
            names += entry.getKey().replaceAll("&", "%26").replaceAll("#","%23") + "|";
            
            for (Integer i : entry.getValue()) {
                values += (i*(100/maxval)) + ",";
            }

            values = values.substring(0, values.length() - 1);
            values += "|";
        }
        
        names = names.substring(0, names.length() - 1);
        values = values.substring(0, values.length() - 1);
        
        for (Date day : dayList) {
            days += "|" + DateUtils.fromDateToString(day);
        }
        
        for (int i=1; i<=maxval; i += 1) {
            points += "|" + i;
        }

        url += "&chd=t:" + values;
        url += "&chxl=0:" + days + "|1:|" + points;
        url += "&chdl=" + names;
        return url;
    }
    
    @Override
    public String toString() {
        return this.createURL();
    }
}
