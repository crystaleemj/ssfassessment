package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.Login;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@RestController
@RequestMapping
public class FrontController {

	@Autowired
	private AuthenticationService svc;

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
	public String loginPage(Model model, @Valid Login login, BindingResult result){

		if (result.hasErrors()){
			model.addAttribute("errormsg", "login failed");
			return "view0";
		}

		model.addAttribute("successmsg", "login successful");
		model.addAttribute("login", login);

		return "view1";
	}

	@PostMapping (path="/api/authenticate",
	consumes="application/json",
	produces="application/json")
	public ResponseEntity<String> authenticate(@PathVariable String user, String password){
		
	}

	@GetMapping(path="{orderId}")
    public ResponseEntity<String> getOrderDetails(@PathVariable String orderId) {
        Order o = svc.getOrderbyId(orderId);
        if (o == null) {
            JsonObject error = Json.createObjectBuilder()
            .add("message", "order id not found")
            .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        }
        return ResponseEntity.ok(o.toJson().toString());
    }

}
