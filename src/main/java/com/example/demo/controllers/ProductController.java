package com.example.demo.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductNamePrice;
import com.example.demo.entity.ProductNamePriceCategory;
import com.example.demo.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	// Create Product
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}
	
	// Update Product
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	
	// Delete Product
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProduct(@RequestParam("id") Integer id) {
		return productService.deleteProduct(id);
	}
	
	// Get Product by ID
	@GetMapping("/getById")
	public Product getById(@RequestParam("id") Integer id) {
		return productService.getById(id);
	}
	
	// Get All Products
	@GetMapping("/getAll")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	// Get All Products by Category
	@GetMapping("/getByCategory")
	public List<Product> getByCategory(@RequestParam("productCategory") String productCategory) {
		return productService.getByCategory(productCategory);
	}
	
	// Get Name, Price and Category
	@GetMapping("/getByCategoryPriceName")
	public List<ProductNamePriceCategory> getByCategoryPriceName(@RequestParam("productCategory") String productCategory) {
		return productService.getByCategoryPriceName(productCategory);
	}
	
	// Get All Products by Origin
	@GetMapping("/getByOrigin")
	public List<Product> getByOrigin(@RequestParam("origin") String origin) {
		return productService.getByOrigin(origin);
	}
	
	// Get Calculated discounted price for Products by Origin
	@GetMapping("/getPriceByOrigin")
	public List<ProductNamePrice> getPriceByOrigin(@RequestParam("origin") String origin) {
		return productService.getPriceByOrigin(origin);
	}
	
	
}
