package com.bits.dissertation.serviceImpl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TemplateOnboardingServiceHelper {
	
	public boolean validateHtml(MultipartFile file) throws IOException { 
			
            Document doc = Jsoup.parse(file.getInputStream(), "UTF-8", "", Parser.htmlParser());
            // Check for errors
            if (checkForErrors(doc) || checkForContentType(file)) {
                System.out.println("The HTML file contains errors.");
                return false;
            } else {
                System.out.println("The HTML file is well-formed.");
                return true;
            }
        
    }
	
	private boolean checkForContentType(MultipartFile file) {
		if ("text/html".equals(file.getContentType())) {
            return true;
        } else {
            return false;
        }
	}

	private static boolean checkForErrors(Document doc) {
        // Check for unclosed tags
        Set<String> unclosedTags = new HashSet<>();
        for (Element element : doc.getAllElements()) {
            // Check if the element is self-closing or not
            if (!element.tag().isSelfClosing() && !element.tag().isKnownTag() && !element.hasText()) {
                unclosedTags.add(element.tagName());
            }
        }
        if (!unclosedTags.isEmpty()) {
            System.out.println("Unclosed tags found: " + unclosedTags);
            return true; // There are errors
        }
        return false; // No errors
    }

}
