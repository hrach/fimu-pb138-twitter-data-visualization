package com.skrasek.school.pb138;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Trend model
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class Trend implements Comparable<Trend>
{

    private Map<String, Integer> data;
    private String name;
    private String query;
    private int sum;

    public Trend(String tag, String query)
    {
        if (tag == null) {
            throw new NullPointerException("tag name");
        }

        this.name = tag;
        this.query = query;
        this.data = new HashMap<String, Integer>();
    }

    public void addTermin(String date)
    {
        date = date.substring(0, 8) + "0000";
        if (data.containsKey(date)) {
            data.put(date, data.get(date) + 1);
        } else {
            data.put(date, 1);
        }
        sum += 1;
    }

    public int getCount(String date)
    {
        date = date.substring(0, 8) + "0000";
        if (data.containsKey(date)) {
            return data.get(date).intValue();
        } else {
            return 0;
        }
    }

    public String getName()
    {
        return this.name;
    }

    public String getQuery()
    {
        return this.query;
    }

    public int compareTo(Trend o)
    {
        return o.sum - this.sum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Trend t = (Trend) o;
        return this.getName().equals(t.getName()) && this.data.equals(t.data);
    }

    @Override
    public int hashCode() {
        int i = 7;
        i += 13 * getName().hashCode();
        i += 17 * data.hashCode();
        return i;
    }

}
