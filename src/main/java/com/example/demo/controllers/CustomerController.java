package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	// Create Customer
	@PostMapping("/create")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	
	// Update Customer
	@PutMapping("/update")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}

	// Delete Customer
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteCustomer(@RequestParam("id") Integer id) {
		return customerService.deleteCustomer(id);
	}
}
