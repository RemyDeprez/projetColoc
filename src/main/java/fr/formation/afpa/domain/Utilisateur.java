package fr.formation.afpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
public class Utilisateur {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "UtilisateurID",unique = true, nullable = false)
	private int utilisateurID;

	@Column(name = "Attributeprenom", nullable = false)
	private String attributeprenom;

	@Column(name = "isActive", nullable = false)
	private byte isActive;

	@Column(name = "login", nullable = false)
	private String login;

	@Column(name = "mail", nullable = false)
	private String mail;
	
	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "motDePasse", nullable = false)
	private String motDePasse;

	@Column(name = "nom", nullable = false)
	private String nom;

	@Column(name = "telephone", nullable = false)
	private int telephone;

	//bi-directional one-to-one association to Proprietaire
	@OneToOne
	@JoinColumn(name="UtilisateurID")
	private Proprietaire proprietaire;

	//bi-directional one-to-one association to Colocataire
	@OneToOne
	@JoinColumn(name="UtilisateurID")
	private Colocataire colocataire;

	public Utilisateur() {
	}

	public int getUtilisateurID() {
		return this.utilisateurID;
	}

	public void setUtilisateurID(int utilisateurID) {
		this.utilisateurID = utilisateurID;
	}

	public String getAttributeprenom() {
		return this.attributeprenom;
	}

	public void setAttributeprenom(String attributeprenom) {
		this.attributeprenom = attributeprenom;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getTelephone() {
		return this.telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public Proprietaire getProprietaire() {
		return this.proprietaire;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Colocataire getColocataire() {
		return this.colocataire;
	}

	public void setColocataire(Colocataire colocataire) {
		this.colocataire = colocataire;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}