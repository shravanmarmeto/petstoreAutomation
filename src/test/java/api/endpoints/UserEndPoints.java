package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints {

	//we perfrom CRUD
	
	public static Response createUser(User payload) {
		Response res = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_Url);
		return res;
	}
	public static Response readUser(String username) {
		Response res = given()
				.pathParam("username", username)
		.when()
		.get(Routes.get_Url);
		return res;
	}
	public static Response updateUser(User payload, String username) {
		Response res = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", username)
		.when()
		.put(Routes.put_Url);
		return res;
	}
	public static Response deleteUser(String username) {
		Response res = given()
				.pathParam("username", username)
		.when()
		.delete(Routes.delete_Url);
		return res;
	}
}
