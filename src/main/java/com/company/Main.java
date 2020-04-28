package com.company;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import jdk.nashorn.internal.runtime.Context;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Main {




    public static void main(String[] args) throws IOException, ParseException, FileNotFoundException {
       JSONObject jo = new JSONObject();
       Map m = new LinkedHashMap(2);
       m.put("BaseUrl", "https://www.amazon.com.mx");
       m.put("BrowserTarget", "Firefox");
       jo.put("AppSettings", m);
       PrintWriter pw = new PrintWriter("JSONExample.json");
       pw.write(jo.toJSONString());
       pw.flush();
       pw.close();

       Main jasonreader = new Main();
        System.out.println(jasonreader.leeJason("BrowserTarget"));


    }

    public static String leeJason(String str1) throws IOException, ParseException {
        try {
            if(str1.matches("[\\s+aA-zZ]+")) {
                Object parse = new JSONParser().parse(new FileReader("JSONExample.json"));
                JSONObject jsonObject = (JSONObject) parse;
                String stringaux = "";
                Map settings = ((Map) jsonObject.get("AppSettings"));
                Iterator<Map.Entry> reader = settings.entrySet().iterator();
                while (reader.hasNext()) {
                    Map.Entry data = reader.next();
                    //stringaux = data.getKey() + " : " + data.getValue();
                    if (data.getKey().equals(str1)) {
                        stringaux = data.getKey() + " : " + data.getValue();
                        return stringaux;
                    } else {
                        stringaux = "That key has no coincidences";
                    }
                }
                return stringaux;
            }
            else return"The key must be a String.";
        }
        catch (Exception e) {
            return "Null is not a known key";
        }
    }
}

