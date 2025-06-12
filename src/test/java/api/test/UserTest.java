package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.base.BaseTest;
import api.endpoints.UserEndPoint;
import api.payload.BookUsers;
import io.restassured.response.Response;

public class UserTest extends BaseTest {

	@Test(priority = 1)
	public void testDuplicateSignUp() {
		Response response = UserEndPoint.signUpUser(testUser);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 400);

		String error = response.jsonPath().getString("detail");
		Assert.assertEquals(error, "Email already registered");
	}

	@Test(priority = 2)
	public void testSignUpWithNoPayloadBody() {    // no emailId or password, no { } 
		 
		Response response = UserEndPoint.signUpUser_noPayLoadBody();
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 422);

		String type = response.jsonPath().getString("detail[0].type");

		String message = response.jsonPath().getString("detail[0].msg");

		Assert.assertEquals(type, "missing");

		Assert.assertEquals(message, "Field required");

	}
	
	
	@Test(priority = 3)
	public void testSignUpWithRawPayloadBody() {  // no emailId or password, with { } 
		
		String emptyJson = "{}"; 

		Response response = UserEndPoint.signUpUser_withRawBody(emptyJson);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 500);

	}
	

	@Test(priority = 4)
	public void testLoginWithValidCredentials() {
		Response response = UserEndPoint.signInUser(testUser);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");

		String accessToken = response.jsonPath().getString("access_token");
		Assert.assertNotNull(accessToken, "Access token should not be null");
		Assert.assertEquals(response.jsonPath().getString("token_type"), "bearer");
	}

	@Test(priority = 5)
	public void testLoginWithWrongPassword() {
		BookUsers wrongCreds = new BookUsers();
		wrongCreds.setEmail(testUser.getEmail());
		wrongCreds.setPassword("WrongPassword");

		Response response = UserEndPoint.signInUser(wrongCreds);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(response.jsonPath().getString("detail"), "Incorrect email or password");
	}

	@Test(priority = 6)
	public void testLoginWithWrongEmail() {
		BookUsers wrongCreds = new BookUsers();
		wrongCreds.setEmail("nonexistent@example.com");
		wrongCreds.setPassword(testUser.getPassword());

		Response response = UserEndPoint.signInUser(wrongCreds);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(response.jsonPath().getString("detail"), "Incorrect email or password");
	}



	@Test(priority = 7)
	public void testLoginWithNoPayload() {    // no email or password  , no {}
		  

		Response response = UserEndPoint.signInUser_noPayLoadBody();
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 422);

		String type = response.jsonPath().getString("detail[0].type");

		String message = response.jsonPath().getString("detail[0].msg");

		Assert.assertEquals(type, "missing");

		Assert.assertEquals(message, "Field required");
	}
	
	@Test(priority = 8)
	public void testSignInWithRawPayloadBody() {  // no emailId or password, with { } 
		
		String emptyJson = "{}"; 

		Response response = UserEndPoint.signUpUser_withRawBody(emptyJson);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 500);

	}
	
}
