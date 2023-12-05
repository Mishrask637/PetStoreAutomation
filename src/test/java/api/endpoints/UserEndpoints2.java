package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {
	
	static ResourceBundle getUrl() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	

	public static Response createUser(User payload) {

		String user_post_url = getUrl().getString("user_post_url");
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.log().body()
				.when()
				.post(user_post_url);

		return response;

	}

	public static Response readUser(String userName) {

		String user_get_url = getUrl().getString("user_get_url");
		
		Response response = given()
				.pathParam("username", userName)
				.when()
				.get(user_get_url);

		return response;

	}

	public static Response updateUser(String userName, User payload) {

		String user_update_url = getUrl().getString("user_update_url");
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.log().body()
				.pathParam("username", userName)
				.when()
				.put(user_update_url);

		return response;

	}

	public static Response deleteUser(String userName) {

		String user_delete_url = getUrl().getString("user_delete_url");
		
		Response response = given()
				.contentType(ContentType.JSON).
				accept(ContentType.JSON).
				pathParam("username", userName)
				.when()
				.delete(user_delete_url);

		return response;

	}

}
