package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true,
			value = "select 	pd.name "
					+ "from 	products pd, "
					+ "			providers pv "
					+ "where	pd.id_providers = pv.id "
					+ "and		pd.amount between :minAmount and :maxAmount "
					+ "and		upper(pv.name) like upper(CONCAT(:nameStartsWith,'%'));")
	List<ProductMinProjection> search1(@Param(value = "minAmount") Integer minAmount, 
			@Param(value = "maxAmount") Integer maxAmount, 
			@Param(value = "nameStartsWith") String nameStartsWith);
	
	@Query("select 	new com.devsuperior.uri2621.dto.ProductMinDTO(pd.name) "
			+ "from 	#{#entityName} pd "
			+ "where	pd.amount between :minAmount and :maxAmount "
			+ "and		upper(pd.provider.name) like upper(CONCAT(:nameStartsWith,'%'))")
	List<ProductMinDTO> search2(@Param(value = "minAmount") Integer minAmount, 
			@Param(value = "maxAmount") Integer maxAmount, 
			@Param(value = "nameStartsWith") String nameStartsWith);
	
	List<Product> findByAmountBetweenAndProviderNameIgnoreCaseStartingWith(
			Integer minAmount, 
			Integer maxAmount, 
			String nameStartsWith);
	
}
