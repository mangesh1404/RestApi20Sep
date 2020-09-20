package com.jbk;


import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Demo {
	Response response;
	// GET POST PUT DELETE---request types
	@Test
	public void VerifyResponseAllUser() {
		
		response = RestAssured.get("https://reqres.in/api/users");

		String responseBody = response.getBody().asString();

		System.out.println("Response Body is =>  " + responseBody);

	}	// Object and String---toString
	// SELECT * From EMPLOYEE WHERE id=2
	
	@Test
	public void VerifyResponseSpecificUser(){   	
		RestAssured.baseURI = "https://reqres.in/api/users";//URL
        RequestSpecification httpRequest = RestAssured.given();//pre--conditions
		Response response = httpRequest.request(Method.GET, "?id=2");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
	
	}	
	
	public String getBodyOfSpecificUser(String id) {
		RestAssured.baseURI = "https://reqres.in/api/users";//URL
        RequestSpecification httpRequest = RestAssured.given();//pre--conditions
		Response response = httpRequest.request(Method.GET, "?id="+id+"");
		String responseBody = response.getBody().asString();
		return responseBody;
	}
	
	@Test
	public void VerifyResponse() {
		
	    response = RestAssured.get("https://reqres.in/api/users");
	     int actualStatusCode = response.getStatusCode();
		System.out.println("Status Code: "+actualStatusCode);
		
		int expCode=200;
		Assert.assertEquals(actualStatusCode, expCode);
		
		String statusLine = response.getStatusLine();
		System.out.println("Status Line: "+statusLine );
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	public void verifyAllHeaders() {
		response = RestAssured.get("https://reqres.in/api/users");
	    Headers allHeaders = response.getHeaders();
		for (Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}
		
	}
	@Test
	public void test5() {
		response = RestAssured.get("https://reqres.in/api/users/2");
	    String contentType = response.header("Content-Type");
    	System.out.println("Content-Type value: " + contentType);
    	String serverType =  response.header("Server");
    	System.out.println("Server value: " + serverType);
    	String date = response.header("Date");
    	System.out.println("Date value: " + date);
        String contentEnco = response.header("Content-Encoding");
    	System.out.println("Content-Encoding: " + contentEnco);
        String transferEnco = response.header("Transfer-Encoding");
    	System.out.println("Transfer-Encoding value: " + transferEnco);   	
	}
	@Test
	public void postUSer() throws Exception {
		RestAssured.baseURI = "https://reqres.in/api/users";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "7");
		requestParams.put("email", "jbk@gmail.com");
		requestParams.put("first_name", "JavaByKiran");
		requestParams.put("last_name", "Pune");
		requestParams.put("avatar", "https://javabykiran.com/");

		JSONObject requestParams1 = new JSONObject();
		requestParams1.put("company", "JBK");
		requestParams1.put("url", "https://javabykiran.com/");
		requestParams1.put("text", "Best Java classes in Pune");
		
		request.body(requestParams.toString());
		request.body(requestParams1.toString());
		
		Response response = request.post("/user");

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 201);
	}
}
