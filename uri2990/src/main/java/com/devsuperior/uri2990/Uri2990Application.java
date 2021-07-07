package com.devsuperior.uri2990;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import com.devsuperior.uri2990.repositories.EmpregadoRepository;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<EmpregadoDeptProjection> list1 = repository.searchSQL1();
		List<EmpregadoDeptDTO> resultSQL1 = list1.stream().map(emp -> new EmpregadoDeptDTO(emp)).collect(Collectors.toList());
		System.out.println("\n*** Resultado SQL 1:");
		resultSQL1.forEach(System.out::println);
		System.out.println("\n\n");

		List<EmpregadoDeptProjection> list2 = repository.searchSQL2();
		List<EmpregadoDeptDTO> resultSQL2 = list2.stream().map(emp -> new EmpregadoDeptDTO(emp)).collect(Collectors.toList());
		System.out.println("\n*** Resultado SQL 2:");
		resultSQL2.forEach(System.out::println);
		System.out.println("\n\n");
		
		List<EmpregadoDeptDTO> resultJPQL2 = repository.searchJPQL2();
		System.out.println("\n*** Resultado JPQL 2:");
		resultJPQL2.forEach(System.out::println);
		System.out.println("\n\n");
		
	}
}
