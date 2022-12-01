package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.masai.exception.ProductException;
import com.masai.model.Category;
import com.masai.model.Products;
import com.masai.model.ProductsDTO;
import com.masai.repository.ProductsDao;

@Service
public class ProductsServiceImpl implements ProductsService{
	
	@Autowired
	private ProductsDao psDao;
	
	@Override
	public Products addProducts(Products products) {
		// TODO Auto-generated method stub
		
		return psDao.save(products);
		
	}

	@Override
	public List<Products> getAllProducts() throws ProductException {
		// TODO Auto-generated method stub
		List<Products> list = psDao.findAll();
		
		if (list.size() > 0) {
			return list;
		} else
			throw new ProductException("No products in catalog");
	}

	@Override
	public Products getProductsFromCatalogById(Integer id) throws ProductException {
		Optional<Products> opt = psDao.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}

		else
			throw new ProductException("Product not found with given id");
	}

	@Override
	public List<ProductsDTO> getCategoryWiseProducts(Category cat) {
		
		List<ProductsDTO> result= psDao.getCategoryWiseProducts(cat);
		
		return result;
	}

	@Override
	public String deleteProductFromCatalog(Integer id) throws ProductException {
		
		Optional<Products> opt=	psDao.findById(id);
			
		if(opt.isPresent()) {
				Products prod = opt.get();
				psDao.delete(prod);
				return "Product deleted from catalog";
			} else
				throw new ProductException("Product not found with given id");
	}

	@Override
	public Products updateProductIncatalog(Products product) throws ProductException {

		Optional<Products> opt = psDao.findById(product.getProductId());

		if (opt.isPresent()) {
			opt.get();
			Products prod1 = psDao.save(product);
			return prod1;
		} else
			throw new ProductException("Product not found with given id");
	}

	
	

	
}