package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Category;
import com.masai.model.Products;
import com.masai.model.ProductsDTO;


@Repository
public interface ProductsDao extends JpaRepository<Products, Integer> {
	
	public List<Products>  findByProductName(String productName);

	@Query("select new com.masai.model.ProductsDTO(p.productName, p.price) from Products p where p.category=?1")
	public List<ProductsDTO> getCategoryWiseProducts(Category cat);

}
