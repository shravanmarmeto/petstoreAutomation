package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints2 {

	
	//Method created for getting URL's from property file
	static ResourceBundle getURL(){
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //here "routes" is the path of property file
		return routes;
	}
	//we perfrom CRUD
	
	public static Response createUser(User payload) {
		//to call above getURL()
		String post_url=getURL().getString("post_url");
		
		Response res = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(post_url);
		return res;
	}
	public static Response readUser(String username) {
		//to call above getURL()
		String get_url=getURL().getString("get_url");
		
		Response res = given()
				.pathParam("username", username)
		.when()
		.get(get_url);
		return res;
	}
	public static Response updateUser(User payload, String username) {
		//to call above getURL()
		String put_url=getURL().getString("update_url");
		Response res = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", username)
		.when()
		.put(put_url);
		return res;
	}
	public static Response deleteUser(String username) {
		//to call above getURL()
		String delete_url=getURL().getString("delete_url");
		Response res = given()
				.pathParam("username", username)
		.when()
		.delete(delete_url);
		return res;
	}
}
