package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiResponse;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductNamePrice;
import com.example.demo.entity.ProductNamePriceCategory;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepo;

	// Create Product
	public ResponseEntity<?> createProduct(Product product) {
		productRepo.save(product);
		return new ResponseEntity(new ApiResponse(true, "Product with name: " + product.getName() + " is created successfully."), HttpStatus.OK);
	}
	
	// Update Product
	public ResponseEntity<?> updateProduct(Product product) {
		productRepo.save(product);
		return new ResponseEntity(new ApiResponse(true, "Product with id: " + product.getId() + " is updated successfully."), HttpStatus.OK);
	}
	
	// Delete Product
	public ResponseEntity<?> deleteProduct(Integer id) {
		productRepo.deleteById(id);
		return new ResponseEntity(new ApiResponse(true, "Product with id: " + id + " is deleted successfully."), HttpStatus.OK);
	}
	
	// Get Product by ID
	public Product getById(Integer id) {
		return productRepo.findById(id).get();
	}

	// Return All Products from DB
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	// Get All Products by Category
	public List<Product> getByCategory(String productCategory) {
		return productRepo.findByCategoryID_ProductCategory(productCategory);
	}
	
	// Get All Products by Category, return Name and Price
	public List<ProductNamePriceCategory> getByCategoryPriceName(String productCategory) {
		List<Product> productsByCategory = getByCategory(productCategory);
		return extreactCategoryPriceName(productsByCategory);
	}

	// Retrieve Product Name, Price and Category from a List of Products
	private List<ProductNamePriceCategory> extreactCategoryPriceName(List<Product> productsByCategory) {
		List<ProductNamePriceCategory> productsResult = new ArrayList<ProductNamePriceCategory>();
		for (Product product : productsByCategory) {
			ProductNamePriceCategory productInfo = new ProductNamePriceCategory();
			productInfo.setName(product.getName());
			productInfo.setPrice(product.getPrice());
			productInfo.setCategory(product.getCategoryID().getProductCategory());
			productsResult.add(productInfo);
		}
		return productsResult;
	}

	// Get All Products by Origin
	public List<Product> getByOrigin(String origin) {
		return productRepo.findByOrigin(origin);
	}
	
	// Get Calculated discounted price for Products by Origin
	public List<ProductNamePrice> getPriceByOrigin(String origin) {
		List<ProductNamePrice> productNamePrice = new ArrayList<ProductNamePrice>();
		List<Product> productsByOrigin = productRepo.findByOrigin(origin);
		
		for (Product product : productsByOrigin) {
			ProductNamePrice productInfo = new ProductNamePrice();
			productInfo.setName(product.getName());
			productInfo.setPriceOld(product.getPrice());
			productInfo.setPriceNew(calculatePrice(product.getPrice(), origin));
			
			productNamePrice.add(productInfo);
		}
		return productNamePrice;
	}

	// Calculate product price based on origin
	private double calculatePrice(Integer price, String origin) {
		if (origin.equalsIgnoreCase("MK")) {
			return price * 0.82;
		}
		return price;
	}

	

	

}
