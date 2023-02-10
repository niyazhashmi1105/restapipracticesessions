package com.restapi.tests;

import static com.restapi.requestbuilder.RequestBuilder.buildRequestForGetCalls;
import static com.restapi.requestbuilder.RequestBuilder.buildRequestForPostCalls;
import static com.restapi.utils.RandomUtils.getFirstName;
import static com.restapi.utils.RandomUtils.getId;
import static com.restapi.utils.RandomUtils.getLastName;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.testng.annotations.Test;
import com.restapi.annotation.FrameworkAnnotation;
import com.restapi.pojo.Employee;
import com.restapi.reports.ExtentLogger;
import com.restapi.utils.ApiUtils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AssignmentTests extends BaseTest{

    @Test
    @FrameworkAnnotation(author = "Niyaz",category = {"Assignment"})
    public void assignmentTest() throws IOException{
        Response response = buildRequestForGetCalls().get("/employees");
        int size = response.jsonPath().getList("$").size();
        System.out.println(size);
        Employee employee = Employee.builder().setFname(getFirstName()).setLname(getLastName()).setId(getId()).build();

        RequestSpecification requestSpecification = buildRequestForPostCalls().body(employee);
        ExtentLogger.logRequest(requestSpecification);

        Response postResponse = requestSpecification.post("/employees");
        ExtentLogger.logResponse(postResponse.asString());

        assertThat(buildRequestForGetCalls().get("/employees").jsonPath().getList("$").size()).isEqualTo(size+1);

        employee.setFname("Default name");
        int id = employee.getId();
        Response putResponse = buildRequestForPostCalls().pathParam("id", id).body(employee).put("/employees/{id}");
        ApiUtils.storeStringAsJsonFile("putresponse.txt",putResponse);
        ExtentLogger.logResponse(putResponse.asString());

        buildRequestForGetCalls().pathParam("id",id).delete("/employees/{id}");

        assertThat(buildRequestForGetCalls().get("/employees").jsonPath().getList("$").size()).isEqualTo(size);


    }
}
