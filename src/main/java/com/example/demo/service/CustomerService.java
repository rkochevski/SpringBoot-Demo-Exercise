package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiResponse;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;

	// Create Customer
	public ResponseEntity<?> createCustomer(Customer customer) {
		customerRepo.save(customer);
		return new ResponseEntity(new ApiResponse(true, "Customer with name: " + customer.getFirstName() + " is created successfully."), HttpStatus.OK);
	}

	// Update Customer
	public ResponseEntity<?> updateCustomer(Customer customer) {
		customerRepo.save(customer);
		return new ResponseEntity(new ApiResponse(true, "Customer with id: " + customer.getId() + " is updated successfully."), HttpStatus.OK);
	}

	// Delete Customer
	public ResponseEntity<?> deleteCustomer(Integer id) {
		customerRepo.deleteById(id);
		return new ResponseEntity(new ApiResponse(true, "Customer with id: " + id + " is deleted successfully."), HttpStatus.OK);
	}

}
