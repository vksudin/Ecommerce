package comTirth.ecommerce.ecommerceProjectWithReact.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import comTirth.ecommerce.ecommerceProjectWithReact.model.Product;

@Repository
public interface RepoInterface extends JpaRepository<Product, Integer> {

	@Query("select pt from Product pt where pt.id= ?1  ")
	Product findsingleProduct(int id);

	@Query("select pt from Product pt where pt.name=?1")
	List<Product> resultKey(String keyword);
}
