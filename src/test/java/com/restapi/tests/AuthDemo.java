package com.restapi.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.restapi.constants.FrameworkConstants;
import com.restapi.utils.ApiUtils;

import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

public class AuthDemo {

    @Test
    public void basicAuthTest(){
        //blacklist headers
        Response response = given()
                .header("Authorization","Basic value")
                .log()
                .all()
                .get("https://postman-echo.com/basic-auth");

        response.prettyPrint();
    }

    @Test
    public void getAllWorkspace(){
                given()
                        .header("X-Api-Key","value")
                        .log()
                        .all()
                        .get("https://api.getpostman.com/workspaces")
                        .prettyPrint();


    }

    @Test
    public void getRepositories(){
        given()
                .header("Authorization","Bearer token")
                .config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                .queryParam("per_page",1)
                .log()
                .all()
                .get("https://api.github.com/user/repos")
                .prettyPrint();
    }


    @Test
    public void createIssue() throws IOException{

        String body = ApiUtils.readJsonAndGetAsString(FrameworkConstants.getRequestJsonFolderpath()+"/request1.json")
                        .replace("KEY","DEM");

        Response response = given()
                .config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization","Content-Type")))
                .header("Authorization","Basic token")
                .header("Content-Type","application/json")
                .log()
                .all()
                .body(body)
                .post("http://localhost:8080/rest/api/2/issue/");

        response.prettyPrint();
    }
}