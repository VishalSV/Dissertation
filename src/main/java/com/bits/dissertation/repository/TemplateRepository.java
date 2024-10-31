package com.bits.dissertation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bits.dissertation.model.TemplateRegistry;


@Repository
public interface TemplateRepository extends JpaRepository<TemplateRegistry, String> {
	
	@Query("SELECT s FROM TemplateRegistry s WHERE s.templateName = :templateName")
	TemplateRegistry findBy(@Param(value = "templateName") String templateName);	
	
}
