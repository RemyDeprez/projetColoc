package fr.formation.afpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
 

public class AppUserForm {
 
    @Id
    @GeneratedValue

    private int userId;
    
	private String attributeprenom;
    

	private String mail;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

	private Date date;
	

	private String nom;
	

	private String status;


	private String telephone;
	

	private MultipartFile photos;
    


    private String userName;
 

    private String encrytedPassword;
    

    private Integer code;
 


    private Integer enabled;
	
	private String confirmPassword;
    
    public AppUserForm() {
    	
    }
    
    public int getUserId() {
        return userId;
    }
 
    public void setUserId(int userId) {
        this.userId = userId;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getEncrytedPassword() {
        return encrytedPassword;
    }
 
    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }
 
    public Integer enabled() {
        return enabled;
    }
 
    public void setEnabled(Integer b) {
        this.enabled = b;
    }
    public String getAttributeprenom() {
		return attributeprenom;
	}

	public void setAttributeprenom(String attributeprenom) {
		this.attributeprenom = attributeprenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public MultipartFile getPhotos() {
		return photos;
	}

	public void setPhotos(MultipartFile photos) {
		this.photos = photos;
	}
    public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Integer getEnabled() {
		return enabled;
	}


	
	
	
	

 
}