package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UsersDD {

	@Test(priority=1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void createUsers(String id,String username,String firstName,String lastName,String email,String password,String phone) {
		
		User payload = new User();
		payload.setId(Integer.parseInt("0"));
		payload.setUserName(username);
		payload.setFirstName(firstName);
		payload.setLastName(lastName);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		
		Response response = UserEndpoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); 
		
	}
	
	@Test(priority=2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void getUsers(String username) {
		
		Response response = UserEndpoints.readUser(username);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); 
		
	}
	
}
