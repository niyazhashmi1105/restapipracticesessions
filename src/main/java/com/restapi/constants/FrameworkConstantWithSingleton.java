package com.restapi.constants;

import lombok.Getter;

	@Getter
	public class FrameworkConstantWithSingleton { 

	    private static FrameworkConstantWithSingleton INSTANCE=null;

	    private FrameworkConstantWithSingleton(){}

	    public static synchronized FrameworkConstantWithSingleton getInstance(){ 
	        if(INSTANCE==null){
	            INSTANCE=new FrameworkConstantWithSingleton();
	        }
	        return INSTANCE;
	    }

	    private  final String requestJsonFolderpath = System.getProperty("user.dir") + "/src/test/resources/jsons/";
	    private  final String responseJsonFolderPath = System.getProperty("user.dir") + "/output/";
}
