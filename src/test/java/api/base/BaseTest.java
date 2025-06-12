package api.base;

import api.endpoints.UserEndPoint;
import api.payload.BookUsers;
import api.utilities.ConfigManager;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	protected static Faker faker;
	protected static BookUsers testUser;
	public static String token;

	@BeforeSuite
	public void setupSuite() {
		
        String env = System.getProperty("env", "dev");  // dev, qa, prod
        ConfigManager.load(env);

        // Set base URI dynamically
        RestAssured.baseURI = ConfigManager.get("base_url");
		
		faker = new Faker();

		// Create user payload
		testUser = new BookUsers();
		testUser.setEmail(
				faker.name().username().replaceAll("[^a-zA-Z0-9]", "") + 
				System.currentTimeMillis() + "@example.com"
				);
		testUser.setPassword("Test123!");

		// Signup the user
		Response signupResp = UserEndPoint.signUpUser(testUser);
		int signupCode = signupResp.getStatusCode();
		
		Assert.assertTrue(signupCode == 200 || signupCode == 400, "Unexpected signup response which is 200, it should be 201");

		if (signupCode == 200) {
			System.out.println("User created successfully");
		}
		
		if (signupCode == 400) {
			System.out.println("User already exists, continuing to login...");
		}

		// Login and extract token
		Response loginResp = UserEndPoint.signInUser(testUser);
		loginResp.then().log().all();

		Assert.assertEquals(loginResp.getStatusCode(), 200, "Login failed");

		token = loginResp.jsonPath().getString("access_token");
		Assert.assertNotNull(token, "JWT token is null!");
		System.out.println("Token retrieved and ready for all tests.");
	}
}
