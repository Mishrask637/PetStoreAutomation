package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	@BeforeClass
	public void setData() {
		faker = new Faker();
		userPayload = new User();	
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setUserName(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		System.out.println("Get id : "+userPayload.getId());
		System.out.println("Get UserName : "+userPayload.getUserName());
		System.out.println("Get FirstName : "+userPayload.getFirstName());
		System.out.println("Get LastName : "+userPayload.getLastName());
		System.out.println("Get password : "+userPayload.getPassword());
		System.out.println("Get email : "+userPayload.getEmail());
		System.out.println("Get phone : "+userPayload.getPhone());
	}
	
	
	@Test(priority = 0)
	public void testCreateUser() {
		Response response = UserEndpoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority = 1)
	public void testGetUser() {
		System.out.println("UserName is "+this.userPayload.getUserName());
		Response response = UserEndpoints2.readUser(this.userPayload.getUserName());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=2)
	public void testUpdateUser() {
		System.out.println("UserName is "+this.userPayload.getUserName());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		Response response = UserEndpoints2.updateUser(this.userPayload.getUserName(),userPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
	}
	
	@Test(priority=3)
	public void testDeleteUser() {
		System.out.println("UserName is "+this.userPayload.getUserName());
		Response response = UserEndpoints2.deleteUser(this.userPayload.getUserName());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
	}
	
	
}
