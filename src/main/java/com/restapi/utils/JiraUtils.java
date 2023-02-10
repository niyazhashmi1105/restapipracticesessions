package com.restapi.utils;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.restapi.constants.FrameworkConstants;

import io.restassured.response.Response;

public final class JiraUtils {

    private JiraUtils(){}

    public static String createIssueInJira(String errormessage) throws IOException{
        String body = ApiUtils.readJsonAndGetAsString(FrameworkConstants.getRequestJsonFolderpath()+"/request1.json")
        					   .replace("KEY","DEM")
        					   .replace("description",errormessage);

        Response response = given()
                .auth()
                .basic("testingminibytes","Ambattur1!")
                //.header("Authorization","dGVzdGluZ21pbmlieXRlczpBbWJhdHR1cjEh")
                .header("Content-Type","application/json")
                .log()
                .all()
                .body(body)
                .post("http://localhost:8080/rest/api/2/issue/");

        response.prettyPrint();
        return response.jsonPath().getString("key");
    }
}
