package api.endpoints;


public class Routes {

	// Authentication
	public static String post_sign_up_url = "/signup";
	public static String post_sign_in_url = "/login";
	
	//books (CRUD)
	public static String post_createBook_url = "/books/";
	public static String get_book_url = "/books/{bookID}";
	public static String get_Allbooks_url = "/books/";
	public static String update_book_url = "/books/{bookID}";
	public static String delete_book_url = "/books/{bookID}";

	// health 
	public static String get_health_check_url = "/health";
}
