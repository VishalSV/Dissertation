package com.bits.dissertation.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bits.dissertation.model.TemplateRegistry;

@Service 
public interface TemplateOnboardingService {

	public TemplateRegistry getTemplateDetails(String templateName);

	public String onboardTemplate(MultipartFile file, String fileName) throws FileNotFoundException;

	public String updateTemplate(MultipartFile file, String fileName, TemplateRegistry existingTemplate) throws IOException;

	public List<TemplateRegistry> getAllTemplateDetails();

}
