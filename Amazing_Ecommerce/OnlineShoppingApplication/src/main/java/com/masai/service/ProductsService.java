package com.masai.service;

import java.util.List;

import com.masai.exception.ProductException;
import com.masai.model.Category;
import com.masai.model.Products;
import com.masai.model.ProductsDTO;

public interface ProductsService {
	
	public Products addProducts(Products products);
	
	public List<Products> getAllProducts() throws ProductException;
	
	public Products getProductsFromCatalogById(Integer id) throws ProductException;
	
	public List<ProductsDTO> getCategoryWiseProducts(Category cat);
	
	public String deleteProductFromCatalog(Integer id) throws ProductException;
	
	public Products updateProductIncatalog(Products product) throws ProductException;

}
