package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.AddressRepository;


@RestController
@RequestMapping(value = "/address")
public class AddressController {
	
	@Autowired
	AddressRepository addressRepo;

}