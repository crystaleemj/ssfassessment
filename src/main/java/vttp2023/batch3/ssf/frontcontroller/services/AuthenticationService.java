package vttp2023.batch3.ssf.frontcontroller.services;

import java.io.StringReader;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2023.batch3.ssf.frontcontroller.model.Login;

public class AuthenticationService {

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public ResponseEntity<String> authenticate(String username, String password) throws Exception {

		// hardcoded the url into String apiUrl as it does not have a key and is not sensitive info
		String apiUrl = "https://authservice-production-e8b2.up.railway.app/api/authenticate";
		
		RequestEntity<String> entity = RequestEntity.post(apiUrl).body(Login.toJSON().toString());

		// create a RestTemplate object to use the .postForEntity() methods
		RestTemplate template = new RestTemplate();

		// create a ResponseEntity to store extracted information from url 
		// (this is now an entire Json object which includes header and body)
        ResponseEntity<String> response = template.exchange(entity, String.class);

		return response;
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
}
