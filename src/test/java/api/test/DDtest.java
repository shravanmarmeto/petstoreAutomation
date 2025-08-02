package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.dataProvider;
import io.restassured.response.Response;

public class DDtest {

	@Test(priority = 1, dataProvider = "data", dataProviderClass = dataProvider.class)
	public void testPostUser(String userid, String uname, String fname, String lname, String email, String pwd, String phone) {
		//Create a POJO in order to set all the fetched values 
		User payload=new User();
		payload.setId(Integer.parseInt(userid)); 
		payload.setUsername(uname);
		payload.setFirstname(fname);
		payload.setLastname(lname);
		payload.setEmail(email);
		payload.setPassword(pwd);
		payload.setPhonenumber(phone);
		
		Response res = UserEndPoints.createUser(payload);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	@Test(priority = 2, dataProvider = "username", dataProviderClass = dataProvider.class)
	public void deleteUser(String username) {
		Response res = UserEndPoints.deleteUser(username);
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
}
