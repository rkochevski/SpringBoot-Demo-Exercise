package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ShoppingCart;
import com.example.demo.service.ShoppingCartService;


@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	// Add Product to ShoppingCart
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestParam("customerId") Integer customerId, @RequestParam("productId") Integer productId) {
		return shoppingCartService.addProduct(customerId, productId);
	}
	
	// Update ShoppingCart
	@PutMapping("/updateCart")
	public ResponseEntity<?> updateCart(@RequestParam("cartId") Integer cartId, @RequestParam("productId") Integer productId) {
		return shoppingCartService.updateCart(cartId, productId);
	}
	
	// Remove Product from ShoppingCart
	@PutMapping("/removeProduct")
	public ResponseEntity<?> removeProduct(@RequestParam("cartId") Integer cartId, @RequestParam("productId") Integer productId) {
		return shoppingCartService.removeProduct(cartId, productId);
	}
	
	// Remove All Products from ShoppingCart
	@PutMapping("/removeAllProducts")
	public ResponseEntity<?> removeAllProducts(@RequestParam("cartId") Integer cartId) {
		return shoppingCartService.removeAllProducts(cartId);
	}
	 
	// Get ShoppingCart by ID
	@GetMapping("/getById")
	public ShoppingCart getById(@RequestParam("cartId") Integer cartId) {
		return shoppingCartService.getById(cartId);
	}

}
