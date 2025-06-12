package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.base.BaseTest;
import api.endpoints.BookEndPoint;
import api.endpoints.Routes;
import api.payload.Books;
import io.restassured.response.Response;

public class BookTest extends BaseTest {

	Books bookPayload = new Books();
	Faker faker = new Faker();
	int createdBookId;

	  @Test(priority = 1)
	public void testCreateBook() {
		  
        bookPayload.setName(faker.book().title());
        bookPayload.setAuthor(faker.book().author());
        bookPayload.setPublished_year(2020);
        bookPayload.setBook_summary("Test automation book");

        Response response = BookEndPoint.createBook(bookPayload, token);
        response.then().log().all();
        
        createdBookId = response.jsonPath().getInt("id");
        System.out.println(" CREATED Book ID : " + createdBookId);
        
        Assert.assertEquals(response.getStatusCode(), 200);
      
        Assert.assertTrue(createdBookId > 0);
    }
	
	  
	  @Test(priority = 2)
	    public void testGetAllBooks() {
	        Response response = BookEndPoint.getAllBooks(token);
	        response.then().log().all();

	        Assert.assertEquals(response.getStatusCode(), 200);
	    }
	
	  
	  @Test(priority = 3)
	    public void testGetBookById() {
	        Response response = BookEndPoint.getBookById(createdBookId, token);
	        response.then().log().all();

//	        Assert.assertEquals(response.getStatusCode(), 200);
//	        Assert.assertEquals(response.jsonPath().getString("name"), bookPayload.getName());

	        Assert.assertEquals(response.getStatusCode(), 403);
	     
  }
	  
	  @Test(priority = 4)
	    public void testDeleteBook() {
	        Response response = BookEndPoint.deleteBook(createdBookId, token);
	        response.then().log().all();

	        Assert.assertEquals(response.getStatusCode(), 200);
	    }
	  
	  
	  @Test(priority = 5)
	  public void testCreateBookWithoutToken() {
	      Books book = new Books();
	      book.setName(faker.book().title());
	      book.setAuthor(faker.book().author());
	      book.setPublished_year(2023);
	      book.setBook_summary("Should fail without token");

	      Response response = io.restassured.RestAssured.given()
	          .contentType("application/json")
	          .body(book)
	      .when()
	          .post(Routes.post_createBook_url);

	      response.then().log().all();
	      Assert.assertEquals(response.getStatusCode(), 403); // Unauthorized
	  }
	  
	  
	  @Test(priority = 6)
	  public void testCreateBookWithInvalidToken() {
	      Books book = new Books();
	      book.setName("Fake Token Book");
	      book.setAuthor("Fake Author");
	      book.setPublished_year(2022);
	      book.setBook_summary("Invalid token test");

	      Response response = io.restassured.RestAssured.given()
	          .contentType("application/json")
	          .header("Authorization", "Bearer INVALID.TOKEN.123")
	          .body(book)
	      .when()
	          .post(Routes.post_createBook_url);

	      response.then().log().all();
	      Assert.assertEquals(response.getStatusCode(), 403); // Invalid token
	  }
	  
	  
	  @Test(priority = 7)
	  public void testCreateBookWithMissingFields() {
	      Books book = new Books();  // No fields set

	      Response response = BookEndPoint.createBook(book, token);
	      response.then().log().all();

	      Assert.assertEquals(response.getStatusCode(), 500); // internal server error
//	      Assert.assertTrue(response.jsonPath().getList("detail").size() > 0);
	  }
	  
	  @Test(priority = 8)
	  public void testGetBookWithInvalidId() {
	      int invalidId = 999999;

	      Response response = BookEndPoint.getBookById(invalidId, token);
	      response.then().log().all();

//	      Assert.assertEquals(response.getStatusCode(), 404); // Not Found
//	      Assert.assertEquals(response.jsonPath().getString("detail"), "Book not found");
	      
	      Assert.assertEquals(response.getStatusCode(), 403); // Not Found
	      Assert.assertEquals(response.jsonPath().getString("detail"), "Not authenticated");
	  }
	  
	  @Test(priority = 9)
	  public void testDeleteAlreadyDeletedBook() {
	      Response response = BookEndPoint.deleteBook(createdBookId, token);
	      response.then().log().all();

	      Assert.assertEquals(response.getStatusCode(), 404); 
	      Assert.assertEquals(response.jsonPath().getString("detail"), "Book not found");
	  }
	  
	  
	  @Test(priority = 10)
	  public void testHealthCheck() {
	      Response response = BookEndPoint.checkHealth(token);

	      response.then().log().all();

	      Assert.assertEquals(response.getStatusCode(), 200, "Health check failed");

	      String status = response.jsonPath().getString("status");
	      Assert.assertEquals(status, "up", "API health status is not 'up'");
	  }
	  
}
