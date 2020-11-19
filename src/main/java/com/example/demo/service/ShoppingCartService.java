package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiResponse;
import com.example.demo.entity.Product;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {
	
	@Autowired
	ShoppingCartRepository shoppingCartRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	ProductRepository productRepo;

	// Add Product to ShoppingCart
	public ResponseEntity<?> addProduct(Integer customerId, Integer productId) {
		ShoppingCart cart = createCart(customerId, productId);
		cart.setCreatedOn(new Date(System.currentTimeMillis()));
		shoppingCartRepo.save(cart);
		return new ResponseEntity(new ApiResponse(true, "Product is added to cart."), HttpStatus.OK);
	}
	
	// Create Cart and link Product and Customer to it
	public ShoppingCart createCart(Integer customerId, Integer productId) {
		List<Product> products = addProductToList(productRepo.findById(productId).get());
		ShoppingCart cart = addCustomerProductsToCart(customerId, products);
		return cart;
	}
	
	// Add Product to List of Products
	public List<Product> addProductToList(Product product) {
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		return products;
	}
	
	// Link Customer and Product to ShoppingCart
	public ShoppingCart addCustomerProductsToCart(Integer customerId, List<Product> products) {
		ShoppingCart cart = new ShoppingCart();
		cart.setCustomerID(customerRepo.findById(customerId).get());
		cart.setProductID(products);
		return cart;
	}

	// Add Product to existing ShoppingCart
	public ResponseEntity<?> updateCart(Integer cartId, Integer productId) {
		ShoppingCart cart = shoppingCartRepo.findById(cartId).get();
		cart.getProductID().add(productRepo.findById(productId).get());
		cart.setCreatedOn(new Date(System.currentTimeMillis()));
		shoppingCartRepo.save(cart);
		return new ResponseEntity(new ApiResponse(true, "Shopping cart updated"), HttpStatus.OK);
	}

	// Remove Product from ShoppingCart
	public ResponseEntity<?> removeProduct(Integer cartId, Integer productId) {
		ShoppingCart cart = shoppingCartRepo.findById(cartId).get();
		cart.getProductID().remove(productRepo.findById(productId).get());
		cart.setCreatedOn(new Date(System.currentTimeMillis()));
		shoppingCartRepo.save(cart);
		return new ResponseEntity(new ApiResponse(true, "Product with id: " + productId + " is removed from cart"), HttpStatus.OK);
	}

	// Remove All Products from ShoppingCart
	public ResponseEntity<?> removeAllProducts(Integer cartId) {
		ShoppingCart cart = shoppingCartRepo.findById(cartId).get();
		cart.getProductID().clear();
		cart.setCreatedOn(new Date(System.currentTimeMillis()));
		shoppingCartRepo.save(cart);
		return new ResponseEntity(new ApiResponse(true, "All products are removed from cart"), HttpStatus.OK);
	}

	// Get ShoppingCart by ID
	public ShoppingCart getById(Integer cartId) {
		return shoppingCartRepo.findById(cartId).get();
	}

}
