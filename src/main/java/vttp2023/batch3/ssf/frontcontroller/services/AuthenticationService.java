package vttp2023.batch3.ssf.frontcontroller.services;

import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import vttp2023.batch3.ssf.frontcontroller.model.Login;

@Service
public class AuthenticationService {

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public boolean authenticate(String username, String password) throws Exception {

		// hardcoded the url into String apiUrl as it does not have a key and is not sensitive info
		String apiUrl = "https://authservice-production-e8b2.up.railway.app/api/authenticate";
		
		Login login = new Login(username, password);
		RequestEntity<String> entity = RequestEntity.post(apiUrl)
													.contentType(MediaType.APPLICATION_JSON)
													.header("Accept", MediaType.APPLICATION_JSON_VALUE)
													.body(login.toJSON().toString(), String.class);

		// create a RestTemplate object to use the .postForEntity() methods
		RestTemplate template = new RestTemplate();

		// create a ResponseEntity to store extracted information from url 
		// (this is now an entire Json object which includes header and body)
        ResponseEntity<String> response = template.exchange(entity, String.class);
		if (response.getStatusCode().is2xxSuccessful()){
			return true;
		} else {
			return false;
		}
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}

	// Task 3 create generateCaptcha() method
    public String generateCaptcha() {
		int num1;
    	int num2;
    	String operator;

		Random random = new Random();
    	String[] operators = {"+", "-", "/", "*"};
        num1 = random.nextInt(50 - 1) + 1;
        num2 = random.nextInt(50 - 1 ) + 1;
        int randomIndex = random.nextInt(3 - 0) + 0;
        operator = operators[randomIndex];

		String captchaGenerated = num1 + " " + operator + " " + num2;

		// Save the correct answer for later validation
		int correctAnswer = calcCaptcha(num1, num2, operator);

		return captchaGenerated;
    }

	// write calcCaptcha method to calculate the correct answer our captcha expects from user
	public int calcCaptcha(int num1, int num2, String operator){

		int answer = 0;
		switch (operator){
			case "+":
			answer = num1 + num2;
			break;

			case "-":
			answer = num1 - num2;
			break;

			case "*":
			answer = num1 * num2;
			break;

			case "/":
			answer = num1 / num2;
			break;

		} return answer;

	}


	// write validateCaptcha method to check if user input is the same as expected input
	public boolean validateCaptcha(int userAnswer, int correctAnswer) {
		return userAnswer == correctAnswer;
	}
	
	
}
