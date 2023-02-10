package com.restapi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.restapi.constants.FrameworkConstants;
import com.restapi.enums.PropertiesType;

public final class PropertyUtils {

    private PropertyUtils(){}

    //read the content from property file and store it in hashmap
    //read the content only once and store it in some java collection

    private static Properties properties= new Properties();
    private static Map<String,String> MAP = new HashMap<>();

    //Generic exception , some one has to make a call
    //incorrect exception is propagated to caller
    //before the whole test execution starts
    static{
        try(FileInputStream inputStream = new FileInputStream(FrameworkConstants.getPropertyFilePath())) { //closeable
            properties.load(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        //finally --> close the input stream connection
        properties.entrySet().forEach(e->MAP.put(String.valueOf(e.getKey()),String.valueOf(e.getValue())));
    }

    public static String getValue(PropertiesType key){
        return MAP.get(key.name().toLowerCase());
    }
}