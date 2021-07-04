package com.devsuperior.uri2621;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import com.devsuperior.uri2621.repositories.ProductRepository;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Integer minAmount = 10;
		Integer maxAmount = 20;
		String startsWith = "p";
		
		List<ProductMinProjection> list = repository.search1(minAmount, maxAmount, startsWith);
		List<ProductMinDTO> result1 = list.stream().map(p -> new ProductMinDTO(p)).collect(Collectors.toList());
		System.out.println("\n*** Resultado SQL RAIZ:");
		result1.forEach(System.out::println);
		System.out.println("\n\n");
		
		List<ProductMinDTO> result2 = repository.search2(minAmount, maxAmount, startsWith);
		System.out.println("\n*** Resultado JPQL:");
		result2.forEach(System.out::println);
		System.out.println("\n\n");
		
		List<Product> result3 = repository.findByAmountBetweenAndProviderNameIgnoreCaseStartingWith(minAmount, maxAmount, startsWith);
		List<ProductMinDTO> result3AsDTO = result3.stream().map(p -> new ProductMinDTO(p)).collect(Collectors.toList());
		System.out.println("\n*** Resultado Query Methods:");
		result3AsDTO.forEach(System.out::println);
		System.out.println("\n\n");

	}
}
