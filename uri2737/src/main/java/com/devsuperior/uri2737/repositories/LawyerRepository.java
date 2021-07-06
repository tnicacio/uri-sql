package com.devsuperior.uri2737.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2737.entities.Lawyer;
import com.devsuperior.uri2737.projections.LawyerMinProjection;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

	@Query(nativeQuery = true,
			value = "(select l.name, l.customers_number as customersNumber from lawyers l "
					+ " where l.customers_number = ( "
					+ " 	select max(x.customers_number) from lawyers x "
					+ " )) "
					+ "union all "
					+ "(select l.name, l.customers_number from lawyers l "
					+ " where l.customers_number = ( "
					+ " 	select min(x.customers_number) from lawyers x "
					+ " )) "
					+ "union all "
					+ "(select 'Average', round(avg(l.customers_number),0) from lawyers l)")
	List<LawyerMinProjection> search1();
}
