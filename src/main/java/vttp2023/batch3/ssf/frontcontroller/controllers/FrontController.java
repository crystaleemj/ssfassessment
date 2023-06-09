package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.Login;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
@RequestMapping
public class FrontController {

	@Autowired
	private AuthenticationService svc;

	int attempt;

	// TODO: Task 2, Task 3, Task 4, Task 6

	// create GET controller for displaying landing page
	@GetMapping
	public String landingPage(Model model){
		model.addAttribute("login", new Login());
		return "view0";
	}

	// create POST controller for logging in (Task 2)
	@PostMapping (path = "/login" ,
	consumes="application/x-www-form-urlencoded",
	produces="text/html")
	public String loginPage(Model model, @Valid Login login, BindingResult result, 
	@RequestParam(value="userAnswer", required=false) String userAnswer, 
	@RequestParam(value="correctAnswer", required= false) String correctAnswer) throws Exception{

		System.out.println(userAnswer);
		System.out.println(correctAnswer);

		// check for validation
		if (result.hasErrors()){
			model.addAttribute("errormsg", "unable to log in");
			return "view0";
		} 
		
		try {
			
			// call authenticate() method to check if user & login is valid
			svc.authenticate(login.getUser(), login.getPassword());

			// return success message accordingly
			model.addAttribute("successmsg", "login successful");
			model.addAttribute("login", login);
			return "view1";
			
		  } catch (Exception ex) {


			attempt++;

			// return error message accordingly 
			model.addAttribute("exceptionmsg", "Invalid login details!");
			model.addAttribute("loginAttempts", attempt);
	  
			if (attempt < 2) {

				// call generateCaptcha() method to generate captcha
			  model.addAttribute("captcha", svc.generateCaptcha());
			}
			 

			// Bind captcha information to thymeleaf model for HTML rendering
			model.addAttribute("correctAnswer", correctAnswer);

		
			// check if the user input and our correct answer is the same, return html views accordingly

			if (userAnswer != null && correctAnswer != null &&
			svc.validateCaptcha(Integer.parseInt(userAnswer), Integer.parseInt(correctAnswer))) {
			return "view1";
			} else {
			return "view0";
			}
			
			// if (svc.validateCaptcha(Integer.parseInt(userAnswer), Integer.parseInt(correctAnswer))) {
			// 	return "view1";
			// } else {
			// 	return "view0";
			// }
			

		}

	

	}
}
