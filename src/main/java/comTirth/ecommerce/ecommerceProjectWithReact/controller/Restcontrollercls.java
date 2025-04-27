package comTirth.ecommerce.ecommerceProjectWithReact.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restcontrollercls {

	@GetMapping("/hello")
	public String sendingHello()
	{
		System.out.println("printing hello..");
		return "Welcome to ecommerce project";
	}
}
