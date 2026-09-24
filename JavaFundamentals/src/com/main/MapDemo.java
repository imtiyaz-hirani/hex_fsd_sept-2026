package com.main;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {

    public static void main(String[] args) {
        // login : username-password
        Map<String,String> map = new HashMap<>();
        // key of type String, value of type String
        map.put("harry@gmail.com", "harry@123"); // entry-set
        map.put("ronald@gmail.com" , "ronald@123"); // entry-set

        map.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " -- " + entry.getValue());
        });

       String value =  map.get("ronald@gmail.com");
        System.out.println(value);
    }
}
/*
List = [3,4,5,6,7,8]
map = [{key,value},{key,value},{key,value},{key,value}]
entrySet = {key,value}
* */