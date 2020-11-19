package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.ManufacturerRepository;


@RestController
@RequestMapping(value = "/manufacturer")
public class ManufacturerController {
	
	@Autowired
	ManufacturerRepository manufacturerRepo;

}
