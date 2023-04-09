package com.hospital.patientadmin.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="procedures")
public interface RequestedProcedureRepository extends CrudRepository<RequestedProcedure,Long> {
	
	List<RequestedProcedure> findByStatus(String status);
	
	List<RequestedProcedure> findByReferrerOrderByStatusAsc(String referrer);
	
	@Query("select p from RequestedProcedure p where p.referrer like ?1%")
	List<RequestedProcedure> stuff(String pref);
	
	List<RequestedProcedure> findByReferrerAndProcedureName(String referrer, String procedureName);


}
