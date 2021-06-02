package fr.formation.afpa.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * The persistent class for the location database table.
 * 
 */

public class LocationForm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int locationID;

	
	private String adress;

	private int idProprietaire;

	private byte isComplet;

	private Integer loyer;

	private int maxColocataire;

	private float note;

	private MultipartFile photos;


	private Integer placeOccupe;


	private Integer superfice;
	

	private String ville;
	

	private Integer codePostal;
	

	private String titre;
	

	private Boolean meuble;

	private String description;

	//bi-directional many-to-one association to Colocataire
	@OneToMany(mappedBy="location")
	private List<Colocataire> colocataires;

	//bi-directional many-to-one association to Evaluation
	@OneToMany(mappedBy="location")
	private List<Evaluation> evaluations;

	//bi-directional many-to-one association to Proprietaire
	@ManyToOne
	@JoinColumn(name="PropriétaireUtilisateurID")
	private Proprietaire proprietaire;

//	//bi-directional many-to-one association to Reservation
	@ManyToOne
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name="ReservationID")
	private Reservation reservation;

	public LocationForm() {
	}
	
	

	public LocationForm(int locationID, String adress, int idProprietaire, byte isComplet, int loyer, int maxColocataire,
			float note, MultipartFile photos, Integer placeOccupe, Integer superfice, String ville, Integer codePostal,
			String titre, Boolean meuble, String description, List<Colocataire> colocataires,
			List<Evaluation> evaluations, Proprietaire proprietaire, Reservation reservation) {
		super();
		this.locationID = locationID;
		this.adress = adress;
		this.idProprietaire = idProprietaire;
		this.isComplet = isComplet;
		this.loyer = loyer;
		this.maxColocataire = maxColocataire;
		this.note = note;
		this.photos = photos;
		this.placeOccupe = placeOccupe;
		this.superfice = superfice;
		this.ville = ville;
		this.codePostal = codePostal;
		this.titre = titre;
		this.meuble = meuble;
		this.description = description;
		this.colocataires = colocataires;
		this.evaluations = evaluations;
		this.proprietaire = proprietaire;
		this.reservation = reservation;
	}



	public int getLocationID() {
		return this.locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}


	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getIdProprietaire() {
		return this.idProprietaire;
	}

	public void setIdProprietaire(int idProprietaire) {
		this.idProprietaire = idProprietaire;
	}

	public byte getIsComplet() {
		return this.isComplet;
	}

	public void setIsComplet(byte isComplet) {
		this.isComplet = isComplet;
	}

	
	public Integer getLoyer() {
		return loyer;
	}



	public void setLoyer(Integer loyer) {
		this.loyer = loyer;
	}



	public int getMaxColocataire() {
		return this.maxColocataire;
	}

	public void setMaxColocataire(int maxColocataire) {
		this.maxColocataire = maxColocataire;
	}

	public float getNote() {
		return this.note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	public MultipartFile getPhotos() {
		return this.photos;
	}

	public void setPhotos(MultipartFile photos) {
		this.photos = photos;
	}

	public Integer getPlaceOccupe() {
		return this.placeOccupe;
	}

	public void setPlaceOccupe(int placeOccupe) {
		this.placeOccupe = placeOccupe;
	}

	public Integer getSuperfice() {
		return this.superfice;
	}

	public void setSuperfice(int superfice) {
		this.superfice = superfice;
	}

	public List<Colocataire> getColocataires() {
		return this.colocataires;
	}

	public void setColocataires(List<Colocataire> colocataires) {
		this.colocataires = colocataires;
	}

	

	public Colocataire removeColocataire(Colocataire colocataire) {
		getColocataires().remove(colocataire);
		colocataire.setLocation(null);

		return colocataire;
	}

	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}



	public Evaluation removeEvaluation(Evaluation evaluation) {
		getEvaluations().remove(evaluation);
		evaluation.setLocation(null);

		return evaluation;
	}

	public Proprietaire getProprietaire() {
		return this.proprietaire;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}



	public Boolean getMeuble() {
		return meuble;
	}



	public void setMeuble(Boolean meuble) {
		this.meuble = meuble;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	
	  @Transient
	    public String getPhotosImagePath() {
	        if (photos == null || locationID <= 0) return null;
	         
	        return "/photos/" + locationID + "/" + photos;
	    }



	public void setPlaceOccupe(Integer placeOccupe) {
		this.placeOccupe = placeOccupe;
	}



	public void setSuperfice(Integer superfice) {
		this.superfice = superfice;
	}
	
	
	

}