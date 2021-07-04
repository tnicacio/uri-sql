package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dto.CustomerNameDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerNameProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String state = "rs";
		List<CustomerNameProjection> list = repository.sqlSearchCustomerNamesFromState(state);
		List<CustomerNameDTO> result1 = list.stream().map(obj -> new CustomerNameDTO(obj)).collect(Collectors.toList());
		
		System.out.println("\n*** Resultado SQL RAIZ:");
		result1.stream().forEach(customer -> System.out.println(customer.getName()));
		
		System.out.println("\n\n");

		List<CustomerNameDTO> result2 = repository.jpqlSearchCustomerNamesFromState(state);
		System.out.println("\n*** Resultado JPQL:");
		result2.stream().forEach(customer -> System.out.println(customer.getName()));

		System.out.println("\n\n");
		
		List<Customer> result3 = repository.findByStateIgnoreCase(state);
		List<CustomerNameDTO> result3AsDTO = result3.stream().map(c -> new CustomerNameDTO(c)).collect(Collectors.toList());
		System.out.println("\n*** Resultado Query Methods:");
		result3AsDTO.stream().forEach(customer -> System.out.println(customer.getName()));
		
		
	}
}
