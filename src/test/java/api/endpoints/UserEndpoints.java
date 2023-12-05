package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	public static Response createUser(User payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.log().body()
				.when()
				.post(Routes.user_post_url);

		return response;

	}

	public static Response readUser(String userName) {

		Response response = given()
				.pathParam("username", userName)
				.when()
				.get(Routes.user_get_url);

		return response;

	}

	public static Response updateUser(String userName, User payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.log().body()
				.pathParam("username", userName)
				.when()
				.put(Routes.user_update_url);

		return response;

	}

	public static Response deleteUser(String userName) {

		Response response = given()
				.contentType(ContentType.JSON).
				accept(ContentType.JSON).
				pathParam("username", userName)
				.when()
				.delete(Routes.user_delete_url);

		return response;

	}

}
