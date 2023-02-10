package com.restapi.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restapi.annotation.FrameworkAnnotation;
import com.restapi.reports.ExtentLogger;
import com.restapi.requestbuilder.RequestBuilder;

import io.restassured.response.Response;


public class GetRequestTests extends BaseTest {

    @Test
    @FrameworkAnnotation(author = {"Niyaz","Hashmi"},category = {"Smoke","Get Call"})
    public void getEmployeesDetails(){


        Response response = RequestBuilder
                .buildRequestForGetCalls()
                .get("/employees");//class or config.properties  //hamcrest

        ExtentLogger.logResponse(response.asPrettyString());

        assertThat(response.getStatusCode()) //Proxy
                .isEqualTo(200);

        assertThat(response.jsonPath().getList("$").size())
                .isPositive()
                .as("Validating the size of the employee array").isGreaterThan(1);
    }

    @Test(dataProvider = "getData")
    @FrameworkAnnotation(author = {"Niyaz"},category = {"Regression","Get Call"})
    public void getEmployeeDetail(Integer id, String lastname){
        Response response = RequestBuilder
                .buildRequestForGetCalls()
                .pathParam("id",id)
                .get("/employees/{id}");//class or config.properties  //hamcrest

        ExtentLogger.logResponse(response.asPrettyString());

        assertThat(response.getStatusCode()) //Proxy
                .isEqualTo(200);

        assertThat(response.jsonPath().getString("last_name"))
                .as("Comparing the last_name node in the response").isEqualTo(lastname)
                .hasSizeBetween(3,20);
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {2,"Palmer"},
                {1,"Eschweiler"},
                {3,"Smith"},
        };
    }
}