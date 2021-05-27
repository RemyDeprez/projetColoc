package fr.formation.afpa.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the colocataire database table.
 * 
 */
@Entity
public class Colocataire {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "uilisateurID",unique = true, nullable = false)
	private int uilisateurID;

	@Column(name = "isOccupe",unique = true, nullable = false)
	private byte isOccupe;

	private String photo;

	private String situationMenage;

	private String situationPro;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="LocationID")
	private Location location;

	//bi-directional one-to-one association to Evaluation
	@OneToOne(mappedBy="colocataire")
	private Evaluation evaluation;

	//bi-directional many-to-one association to Réservation
	@OneToMany(mappedBy="colocataire")
	private List<Reservation> réservations;

	//bi-directional one-to-one association to Utilisateur
	@OneToOne(mappedBy="colocataire")
	private Utilisateur utilisateur;

	public Colocataire() {
	}

	public int getUilisateurID() {
		return this.uilisateurID;
	}

	public void setUilisateurID(int uilisateurID) {
		this.uilisateurID = uilisateurID;
	}

	public byte getIsOccupe() {
		return this.isOccupe;
	}

	public void setIsOccupe(byte isOccupe) {
		this.isOccupe = isOccupe;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSituationMenage() {
		return this.situationMenage;
	}

	public void setSituationMenage(String situationMenage) {
		this.situationMenage = situationMenage;
	}

	public String getSituationPro() {
		return this.situationPro;
	}

	public void setSituationPro(String situationPro) {
		this.situationPro = situationPro;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Evaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public List<Reservation> getRéservations() {
		return this.réservations;
	}

	public void setRéservations(List<Reservation> réservations) {
		this.réservations = réservations;
	}

	public Reservation addRéservation(Reservation réservation) {
		getRéservations().add(réservation);
		réservation.setColocataire(this);

		return réservation;
	}

	public Reservation removeRéservation(Reservation réservation) {
		getRéservations().remove(réservation);
		réservation.setColocataire(null);

		return réservation;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}