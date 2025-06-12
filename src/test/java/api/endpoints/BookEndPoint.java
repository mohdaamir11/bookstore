package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Books;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class BookEndPoint {

	public static Response createBook(Books payload, String token) {
		return given()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer " + token)
				.body(payload)
				.when()
				.post(Routes.post_createBook_url);
	}


	public static Response getAllBooks(String token) {
		return given()
				.header("Authorization", "Bearer " + token)
				.when()
				.get(Routes.get_Allbooks_url);
	}

	public static Response getBookById(int bookId, String token) {

		return given().header("Authorization","Bearer" + token)
				.pathParam("bookID", bookId)
				.when()
				.get(Routes.get_book_url);
	}
	
	
	 public static Response deleteBook(int bookId, String token) {
	        return given()
	                .header("Authorization", "Bearer " + token)
	                .pathParam("bookID", bookId)
	            .when()
	                .delete(Routes.delete_book_url);
	    }


	 public static Response checkHealth(String token) {
	        return given()
	                .header("Authorization", "Bearer " + token)
	            .when()
	                .get(Routes.get_health_check_url);
	    }
}
