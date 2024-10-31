package com.bits.dissertation.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "TemplateRegistry")
public class TemplateRegistry implements Serializable{
	
	@Id
	private String templateName;
	
	@Column(name = "Template")
	private String template;
	
	@Column(name = "TemplateDesc")
	private String templateDesc;
	
	@Column(name = "TemplateOnboardDate")
	private Date templateOnboardDate;
	
	@Column(name = "TemplateUpdateDate")
	private Date templateUpdateDate;

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTemplateDesc() {
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	public Date getTemplateOnboardDate() {
		return templateOnboardDate;
	}

	public void setTemplateOnboardDate(Date templateOnboardDate) {
		this.templateOnboardDate = templateOnboardDate;
	}

	public Date getTemplateUpdateDate() {
		return templateUpdateDate;
	}

	public void setTemplateUpdateDate(Date templateUpdateDate) {
		this.templateUpdateDate = templateUpdateDate;
	}

	@Override
	public String toString() {
		return "TemplateRegistry [templateName=" + templateName + ", template=" + template + ", templateDesc="
				+ templateDesc + ", templateOnboardDate=" + templateOnboardDate + ", templateUpdateDate="
				+ templateUpdateDate + "]";
	}	

}
