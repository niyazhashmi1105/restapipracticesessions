package com.restapi.requestbuilder;

import com.restapi.enums.PropertiesType;
import com.restapi.utils.PropertyUtils;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public final class RequestBuilder {
	
	  private RequestBuilder(){}

	    public static RequestSpecification buildRequestForGetCalls(){
	        return given()
	                .baseUri(PropertyUtils.getValue(PropertiesType.BASEURL))
	                .log()
	                .all();
	    }
	    public static RequestSpecification buildRequestForPostCalls(){
	        return buildRequestForGetCalls()
	                .contentType(ContentType.JSON);
	    }

}
