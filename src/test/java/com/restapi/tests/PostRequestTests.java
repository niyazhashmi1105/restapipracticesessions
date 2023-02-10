package com.restapi.tests;

import static com.restapi.constants.FrameworkConstantWithSingleton.getInstance;
import static com.restapi.utils.RandomUtils.*;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.lang.reflect.Method;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.restapi.annotation.FrameworkAnnotation;
import com.restapi.constants.FrameworkConstants;
import com.restapi.pojo.Employee;
import com.restapi.reports.ExtentLogger;
import com.restapi.requestbuilder.RequestBuilder;
import com.restapi.utils.ApiUtils;
import com.restapi.utils.RandomUtils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestTests extends BaseTest{

    @Test
    @FrameworkAnnotation(author = {"Niyaz"},category = {"Smoke","Post Call"})
    public void postTestUsingPojo(){
        //Create an employee in the db using post call
        //construct using pojo and lombak builder

        Employee employee = Employee
                .builder()
                .setFname(getFirstName())
                .setLname(getLastName())
                .setId(getId()) //new Faker().idNumber().between()
                .build();

        RequestSpecification requestSpecification = given().baseUri("http://localhost:3000")
                .log()
                .all()
                .body(employee);
        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");
        response.prettyPrint();

        ExtentLogger.logResponse(response.asPrettyString());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);

    }

    @Test
    @FrameworkAnnotation(author = {"Niyaz"},category = {"Regression","Post Call"})
    public void postRequestUsingExternalFile(Method method) throws IOException{ //throws IOEXception
        String resource = ApiUtils
                .readJsonAndGetAsString(FrameworkConstants.getRequestJsonFolderpath()+"request.json")
                .replace("fname", RandomUtils.getFirstName())
                .replace("id",String.valueOf(RandomUtils.getId()));

        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .body(resource);

        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");
        response.prettyPrint();

        ExtentLogger.logResponse(response.asPrettyString());
        ApiUtils.storeStringAsJsonFile(FrameworkConstants.getResponseJsonFolderPath()+method.getName()+"response.json",response);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Test
    @FrameworkAnnotation
    public void postRequestUsingExternalFile1(Method method) throws IOException{ //throws IOEXception
        String resource = ApiUtils
                .readJsonAndGetAsString(
                        getInstance().getRequestJsonFolderpath() +"request.json")
                .replace("fname", RandomUtils.getFirstName())
                .replace("id",String.valueOf(RandomUtils.getId())); //arrange

        //Interface
        //

        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .body(resource);

        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");
        response.prettyPrint(); //actions


        ExtentLogger.logResponse(response.asPrettyString());

        ApiUtils.storeStringAsJsonFile(getInstance().getResponseJsonFolderPath()+method.getName()+"response.json",response);
        //response schema validation
        Assertions.assertThat(response.getStatusCode()).isEqualTo(201); //assertions



    }
}
