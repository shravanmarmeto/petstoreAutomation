package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class userTest {
//First we will set the values
	Faker f;
	// creat object of User POJO class
	User payLoad;

	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		f = new Faker();
		payLoad = new User();
		payLoad.setId(f.idNumber().hashCode()); // hascode will generate random number
		payLoad.setUsername(f.name().username());
		payLoad.setFirstname(f.name().firstName());
		payLoad.setLastname(f.name().lastName());
		payLoad.setEmail(f.internet().safeEmailAddress());
		payLoad.setPassword(f.internet().password(5, 10));
		payLoad.setPhonenumber(f.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		logger.debug("---DEBUGGING---");
	}

	@Test(priority = 1)
	public void testPostUser() {
		logger.info("***** Creating User *****");
		Response res = UserEndPoints.createUser(payLoad);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("***** User Created *****");

	}

	@Test(priority = 2)
	public void getUser() {
		logger.info("***** Reading User *****");

		Response res = UserEndPoints.readUser(this.payLoad.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("***** User details disaplyed *****");

	}

	@Test(priority = 3)
	public void updatedUser() {
		logger.info("***** Updating User *****");

		payLoad.setFirstname(f.name().firstName());
		payLoad.setLastname(f.name().lastName());
		payLoad.setEmail(f.internet().safeEmailAddress());

		Response res = UserEndPoints.updateUser(payLoad, this.payLoad.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		//if i want to check whether the data is updated, then i can use GET request again
		//Response afterRes = UserEndPoints.readUser(this.payLoad.getUsername());
		//Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("***** User Updated *****");

	}
	
	@Test(priority = 4)
	public void deleteUser() {
		logger.info("***** Deleting User *****");

		Response res = UserEndPoints.deleteUser(this.payLoad.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);

		logger.info("***** User Deleted *****");

	}
}
