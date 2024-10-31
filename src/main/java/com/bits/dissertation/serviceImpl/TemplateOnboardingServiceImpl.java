package com.bits.dissertation.serviceImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bits.dissertation.model.TemplateRegistry;
import com.bits.dissertation.repository.TemplateRepository;
import com.bits.dissertation.service.TemplateOnboardingService;

@Service
public class TemplateOnboardingServiceImpl implements TemplateOnboardingService {
	
	@Autowired
	private TemplateRepository templateRepo;
	
	Logger logger
    = LoggerFactory.getLogger(TemplateOnboardingServiceImpl.class);
	
	@Override
	public TemplateRegistry getTemplateDetails(String templateName) {
		
		TemplateRegistry templateDetails = templateRepo.findBy(templateName);		
		logger.info("Template details : {}",templateDetails);
		
		return templateDetails;		
		
	}
	
	@Override
	public String onboardTemplate(MultipartFile file, String fileName) throws FileNotFoundException {
		
		TemplateRegistry templateDetails = new TemplateRegistry();
		
       try {
        	StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));            
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            logger.info(content.toString());
            
            templateDetails.setTemplate(content.toString());
            templateDetails.setTemplateDesc(fileName);
            templateDetails.setTemplateName(fileName);
            templateDetails.setTemplateOnboardDate(new Date(System.currentTimeMillis()));
            templateDetails.setTemplateUpdateDate(null);
            
            templateRepo.save(templateDetails);
            
            return "The Template is added with Name: "+fileName;
            
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
		
    }

	@Override
	public String updateTemplate(MultipartFile file, String fileName, TemplateRegistry existigTemplate) throws IOException {
		
		TemplateRegistry templateDetails = new TemplateRegistry();
		
		StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));            
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        logger.info(content.toString());
        
        templateDetails.setTemplate(content.toString());
        templateDetails.setTemplateUpdateDate(new Date(System.currentTimeMillis()));
        templateDetails.setTemplateName(fileName);
        templateDetails.setTemplateDesc(existigTemplate.getTemplateDesc());
        templateDetails.setTemplateOnboardDate(existigTemplate.getTemplateOnboardDate());        
        
        templateRepo.save(templateDetails);
        
        return "The Template is update with Name: "+fileName;
	}

	@Override
	public List<TemplateRegistry> getAllTemplateDetails() {
		
		List<TemplateRegistry> allTemplateDetails = templateRepo.findAll();		
		logger.info("Template details : {}",allTemplateDetails);
		
		return allTemplateDetails;	
	}
}
