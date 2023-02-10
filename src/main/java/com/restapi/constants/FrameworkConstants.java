package com.restapi.constants;

import lombok.Getter;

public class FrameworkConstants {

	//If it non static --> Class level

	@Getter private static final String requestJsonFolderpath = System.getProperty("user.dir")+ "/src/test/resources/jsons/";
	@Getter private static final String responseJsonFolderPath = System.getProperty("user.dir") + "/output/";
	@Getter private static final String propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";

	
}
