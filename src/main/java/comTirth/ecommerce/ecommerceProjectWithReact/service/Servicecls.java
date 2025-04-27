package comTirth.ecommerce.ecommerceProjectWithReact.service;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comTirth.ecommerce.ecommerceProjectWithReact.Repository.RepoInterface;
import org.springframework.web.multipart.MultipartFile;
import comTirth.ecommerce.ecommerceProjectWithReact.model.Product;
import jakarta.transaction.Transactional;

@Service
public class Servicecls {

	@Autowired
	private RepoInterface repo;
	
	public List<Product> servicemethod()
	{
		List<Product> all =repo.findAll();
		System.out.println(all);
		return repo.findAll();
	}
	
	@Transactional
	public Product findsingleProduct(int id)
	{
		System.out.println("from singleProduct method/servicecls");
		return repo.findsingleProduct(id);
	}
	
	public Product addorupdateProd(Product prod, MultipartFile image) throws IOException
	{
		prod.setImageName(image.getOriginalFilename());
		prod.setImageType(image.getContentType());
		prod.setImageDate(image.getBytes());
		
		return repo.save(prod);
	}

	public void deleteProd(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	public List<Product> searchedwithKeyword(String keyword) {
		// TODO Auto-generated method stub
		return repo.resultKey(keyword);
	}
	
	/*public Product addorupdateProd(Product prod, MultipartFile image) throws IOException
	{
		prod.setImageName(image.getOriginalFilename());
		prod.setImageType(image.getContentType());
		prod.setImageDate(image.getBytes());
		
		return repo.save(prod);
	}*/
	
	
	
}
