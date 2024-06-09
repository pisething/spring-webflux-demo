package com.piseth.java.school.spring_webflux_demo.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.piseth.java.school.spring_webflux_demo.dto.MultiplyRequestDTO;
import com.piseth.java.school.spring_webflux_demo.dto.ResponseDTO;
import com.piseth.java.school.spring_webflux_demo.exception.InputFailedValidationException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {

	public Mono<ResponseDTO> findSquare(int input){
		return Mono.fromSupplier(() -> input * input)
				.map(x -> new ResponseDTO(x));
	}
	
	// Error Signal
	public Mono<ResponseDTO> findSquareWithException(int input){
		return Mono.fromSupplier(() -> input )
				.handle((number, sink) ->{
					if(number < 10 || number > 20) {
						sink.error(new InputFailedValidationException(input));
					}else {
						sink.next(new ResponseDTO(input * input));
					}
				});
	}
	
	public Flux<ResponseDTO> multiplicationTable(int input){
		return Flux.range(1, 10)
					//.doOnNext(x -> SleepUtil.sleepSecond(1))
					.delayElements(Duration.ofSeconds(1))
					.doOnNext(x -> System.out.println("Reactive math Service is proccessing "+ x))
					.map(x -> new ResponseDTO(x * x));
	}
	/*
	This is not reactive. Bad practice!!!!
	public Flux<ResponseDTO> multiplicationTable2(int input){
		List<ResponseDTO> list = IntStream.rangeClosed(1, 10)
		.peek(x -> SleepUtil.sleepSecond(1))
		.peek(x -> System.out.println("Math Service is proccessing "+ x))
		.map(x -> x * input)
		.mapToObj(x -> new ResponseDTO(x))
		.toList();
		
		return Flux.fromIterable(list); 
	}
	*/
	
	public Mono<ResponseDTO> multiply(Mono<MultiplyRequestDTO> multiplyMono){
		return multiplyMono
				.map(x -> new ResponseDTO(x.getFirst() * x.getSecond()));
	}
}
