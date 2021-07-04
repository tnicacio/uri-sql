package com.devsuperior.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {
	
	@Autowired
	private MovieRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String genre = "action";
		List<MovieMinProjection> list = repository.search1(genre);
		List<MovieMinDTO> result1 = list.stream()
				.map(movie -> new MovieMinDTO(movie))
				.collect(Collectors.toList());
		
		System.out.println("\n*** Resultado SQL RAIZ:");
		result1.forEach(System.out::println);
		
		System.out.println("\n\n");
		
		List<MovieMinDTO> result2 = repository.search2(genre);
		System.out.println("\n*** Resultado JPQL:");
		result2.forEach(System.out::println);
		
		System.out.println("\n\n");
		
		List<Movie> result3 = repository.findByGenreDescriptionIgnoreCase(genre);
		List<MovieMinDTO> result3AsDTO = result3.stream().map(movie -> new MovieMinDTO(movie)).collect(Collectors.toList());
		System.out.println("\n*** Resultado Query Methods:");
		result3AsDTO.forEach(System.out::println);
	}
}
