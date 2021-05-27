package fr.formation.afpa.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
public class Location {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int locationID;

	private String adress;

	private int idProprietaire;

	private byte isComplet;

	private double loyer;

	private int maxColocataire;

	private float note;

	private String photos;

	private int placeOccupe;

	private int superfice;

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

	//bi-directional many-to-one association to Reservation
	@ManyToOne
	@JoinColumn(name="ReservationID")
	private Reservation reservation;

	public Location() {
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

	public double getLoyer() {
		return this.loyer;
	}

	public void setLoyer(double loyer) {
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

	public String getPhotos() {
		return this.photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public int getPlaceOccupe() {
		return this.placeOccupe;
	}

	public void setPlaceOccupe(int placeOccupe) {
		this.placeOccupe = placeOccupe;
	}

	public int getSuperfice() {
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

	public Colocataire addColocataire(Colocataire colocataire) {
		getColocataires().add(colocataire);
		colocataire.setLocation(this);

		return colocataire;
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

	public Evaluation addEvaluation(Evaluation evaluation) {
		getEvaluations().add(evaluation);
		evaluation.setLocation(this);

		return evaluation;
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

}