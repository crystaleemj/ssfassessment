package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FrontController {

	// TODO: Task 2, Task 3, Task 4, Task 6

	// create controller for displaying landing page
	@GetMapping
	public String landingPage(Model model){

		return "view0";
	}
}
