package fr.formation.afpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;
 
@Entity
@Table(name = "App_User", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name") })
public class AppUser {
 
    @Id
    @GeneratedValue
    @Column(name = "User_Id", nullable = false)
    private Long userId;
    
    @Column(name = "Attributeprenom", nullable = false)
	private String attributeprenom;
    
    @Column(name = "mail", nullable = false)
	private String mail;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "date", nullable = false)
	private Date date;
	
	@Column(name = "nom", nullable = false)
	private String nom;
	
	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "telephone", nullable = false)
	private int telephone;
	
	@Column(name = "photos", nullable = false)
	private String photos;
    

	@Column(name = "User_Name", length = 36, nullable = false)
    private String userName;
 
    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String encrytedPassword;
    
    @Column(name = "code", length = 4)
    private Integer code;
 

	@Column(name = "enabled", length = 1)
    private Integer enabled;
    
    public AppUser() {
    	
    }
    
    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
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

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
    public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

 
}