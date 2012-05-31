/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skrasek.school.pb138;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class Trend {

    private Map<String, Integer> data;
    private String name;
    private String query;

    Trend(String tag, String query)
    {
        this.name = tag;
        this.query = query;
        this.data = new HashMap<String, Integer>();
    }

    void addTermin(String date)
    {
        if (data.containsKey(date)) {
            data.put(date, data.get(date) + 1);
        } else {
            data.put(date, 1);
        }
    }

    int getCount(String date)
    {
        if (data.containsKey(date)) {
            return data.get(date).intValue();
        } else {
            return 0;
        }
    }

    String getName()
    {
        return this.name;
    }

    String getQuery()
    {
        return this.query;
    }

}
