package com.bits.dissertation.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bits.dissertation.model.TemplateRegistry;
import com.bits.dissertation.service.TemplateOnboardingService;

@RestController("TemplateOnboarding")
@RequestMapping(path = "/template", produces = MediaType.APPLICATION_JSON_VALUE)
public class TemplateOnboarding {
	
	@Autowired
	private TemplateOnboardingService templateOnboardingService;
	
	Logger logger
    = LoggerFactory.getLogger(TemplateOnboarding.class);
	
	/**
	 * 
	 * @return
	 * @throws FileNotFoundException 
	 */
	@PostMapping(path = "/onboarding", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, "multipart/form-data;charset=UTF-8"})
	public ResponseEntity<String> onboardTemplate(@RequestParam("file") MultipartFile file) throws FileNotFoundException{
		
		String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().length() - 5);
		
		logger.info("Recevied File : {}", file.getOriginalFilename());
		
		TemplateRegistry existingTemplate = templateOnboardingService.getTemplateDetails(fileName);
		
		if(!ObjectUtils.isEmpty(existingTemplate)) {
			return new ResponseEntity<>("The File Name already Exists, please check existing data : " + existingTemplate.toString(), HttpStatus.NOT_ACCEPTABLE);			
		}
		else {
			return new ResponseEntity<>(templateOnboardingService.onboardTemplate(file , fileName), HttpStatus.OK);	
		}		    
			
	}
	
	/**
	 * 
	 * @param templateName
	 * @return
	 */
	@GetMapping(path = "/fetch/{templateName}")
	public ResponseEntity<Object> getTemplate(@PathVariable(required = true) String templateName){
		
		TemplateRegistry existingTemplate = templateOnboardingService.getTemplateDetails(templateName);
		
		if(ObjectUtils.isEmpty(existingTemplate)){
			return new ResponseEntity<>("Requested Template does not exist.", HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>("Requested Template: "+ existingTemplate, HttpStatus.OK);
		}
		
	}
	
	@PutMapping(path = "/update", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, "multipart/form-data;charset=UTF-8"})
	public ResponseEntity<Object> updateTemplate(@RequestParam("file") MultipartFile file) throws IOException{
		
		String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().length() - 5);		
		logger.info("Recevied File : {}", file.getOriginalFilename());
		
		TemplateRegistry existingTemplate = templateOnboardingService.getTemplateDetails(fileName);
		
		if(ObjectUtils.isEmpty(existingTemplate)){
			return new ResponseEntity<>("Requested Template does not exist.", HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(templateOnboardingService.updateTemplate(file , fileName, existingTemplate), HttpStatus.OK);
		}
		
	}

}
