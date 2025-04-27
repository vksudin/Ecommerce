package comTirth.ecommerce.ecommerceProjectWithReact.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import comTirth.ecommerce.ecommerceProjectWithReact.model.Product;
import comTirth.ecommerce.ecommerceProjectWithReact.service.Servicecls;
import lombok.Getter;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private Servicecls service;
	
	@GetMapping("/products")
	public List<Product> getAllproduct()
	{
		System.out.println("Hi products");
		return service.servicemethod();
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<List<Product>> findsingleProduct(@PathVariable int id)
	{
		Product product =(Product) service.findsingleProduct(id);
		if(product!=null)
			return new ResponseEntity(product,HttpStatus.ACCEPTED); 
		else
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
		//System.out.println("from singleProduct method/controller");
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart("prod") Product product, @RequestPart("image") MultipartFile imageFile)
	{
		Product gotProd=null;
		
		try {
			//System.out.println(product.getName());
			gotProd=service.addorupdateProd(product,imageFile);
			return new ResponseEntity<>(gotProd,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id , @RequestPart Product product, @RequestPart MultipartFile imageFile)
	{
		Product updateProd=null;
		
		try {
			//System.out.println(product.getName());
			updateProd=service.addorupdateProd(product,imageFile);
			return new ResponseEntity<>("updated",HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@DeleteMapping("/product/{id}")
	public String deleteProduct(@PathVariable int id)
	{
		Product dltprod=(Product) service.findsingleProduct(id);
		if(dltprod!=null)
		{
			service.deleteProd(id);
			return "deleted";
		}
		else
			return "not possible";	
	}
	
	
	
	
	
	@GetMapping("/product/{id}/image")
	public ResponseEntity<byte[]> getImage(@PathVariable int id) {
	    Product product = service.findsingleProduct(id); // don't cast
	    byte[] imageData = product.getImageDate();       // assuming image is saved as byte[]

	    if(product.getId()>0)
	    	return new ResponseEntity<>(product.getImageDate(), HttpStatus.OK);
	    else
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	
	@GetMapping("products/search")
	public ResponseEntity<List<Product>> searchMethod(@PathVariable String Keyword)
	{
		List<Product> searchedproduct = service.searchedwithKeyword(Keyword);
		try {
			if(searchedproduct.size()>0)
				return new ResponseEntity(searchedproduct, HttpStatus.OK);
			else
				return new ResponseEntity(searchedproduct, HttpStatus.NOT_FOUND);
			}
		catch(Exception e)
		{
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	
}
