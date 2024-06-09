package com.piseth.java.school.spring_webflux_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.spring_webflux_demo.dto.ResponseDTO;
import com.piseth.java.school.spring_webflux_demo.service.MathService;

@RestController
@RequestMapping("math")
public class MathController {
	
	@Autowired
	private MathService mathService;
	
	@GetMapping("square/{input}")
	public ResponseDTO findSquare(@PathVariable int input) {
		return mathService.findSquare(input);
	}
	
	@GetMapping("table/{input}")
	public List<ResponseDTO> table(@PathVariable int input) {
		return mathService.multiplicationTable(input);
	}
	
	

}
