package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {
	
	@Query(nativeQuery = true,
			value = "select e.cpf, e.enome, d.dnome "
					+ "from	empregados e "
					+ "inner join	departamentos d on e.dnumero = d.dnumero "
					+ "left join trabalha t on t.cpf_emp = e.cpf "
					+ "where t.cpf_emp is null "
					+ "group by e.cpf, d.dnome "
					+ "order by e.cpf")
	List<EmpregadoDeptProjection> searchSQL1();

	@Query(nativeQuery = true,
			value = "select e.cpf, e.enome, d.dnome "
					+ "from	empregados e "
					+ "inner join	departamentos d on e.dnumero = d.dnumero "
					+ "where	e.cpf not in ( "
					+ "	select	empregados.cpf "
					+ "	from	empregados "
					+ "	inner join	trabalha on trabalha.cpf_emp = empregados.cpf "
					+ ") "
					+ "order by e.cpf")
	List<EmpregadoDeptProjection> searchSQL2();

	@Query("select new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(e.cpf, e.enome, e.departamento.dnome) "
			+ "from	Empregado e "
			+ "where e.cpf not in ( "
			+ "	select obj.cpf"
			+ "	from  Empregado obj "
			+ " inner join obj.projetosOndeTrabalha "
			+ ") "
			+ "order by e.cpf")
	List<EmpregadoDeptDTO> searchJPQL2();
}
