package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.BookUsers;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
// for crud operations

public class UserEndPoint {

	public static Response signUpUser(BookUsers payload){
		
		   Response response = given()
		       .contentType(ContentType.JSON)
		       .accept(ContentType.JSON)
		       .body(payload)
		     .when()
		       .post(Routes.post_sign_up_url);
		   
		   return response;
				
			}
	
	public static Response signUpUser_noPayLoadBody(){
		
		   Response response = given()
		       .contentType(ContentType.JSON)
		       .accept(ContentType.JSON)
		     .when()
		       .post(Routes.post_sign_up_url);
		   
		   return response;
				
			}
	
	public static Response signUpUser_withRawBody(String rawBody){
		
		   Response response = given()
		       .contentType(ContentType.JSON)
		       .accept(ContentType.JSON)
		       .body(rawBody)
		     .when()
		       .post(Routes.post_sign_up_url);
		   
		   return response;
				
			}
	
	
	public static Response signInUser(BookUsers payload){
		
		   Response response = given()
		       .contentType(ContentType.JSON)
		       .accept(ContentType.JSON)
		       .body(payload)
		     .when()
		       .post(Routes.post_sign_in_url);
		   
		   return response;
				
			}
	
	public static Response signInUser_noPayLoadBody(){
		
		   Response response = given()
		       .contentType(ContentType.JSON)
		       .accept(ContentType.JSON)
		     .when()
		       .post(Routes.post_sign_in_url);
		   
		   return response;
				
			}
	
	public static Response signInUser_withRawBody(String rawBody){
		
		   Response response = given()
		       .contentType(ContentType.JSON)
		       .accept(ContentType.JSON)
		       .body(rawBody)
		     .when()
		       .post(Routes.post_sign_in_url);
		   
		   return response;
				
			}
	
}
