package fr.formation.afpa.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluation database table.
 * 
 */
@Entity
public class Evaluation implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int evaluationID;

	private String avis;

	private int note;

	//bi-directional one-to-one association to Colocataire
	@OneToOne
	@JoinColumn(name="EvaluationID")
	private Colocataire colocataire;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="LocationID")
	private Location location;

	//bi-directional one-to-one association to Proprietaire
	@OneToOne
	@JoinColumn(name="EvaluationID")
	private Proprietaire proprietaire;

	public Evaluation() {
	}

	public int getEvaluationID() {
		return this.evaluationID;
	}

	public void setEvaluationID(int evaluationID) {
		this.evaluationID = evaluationID;
	}

	public String getAvis() {
		return this.avis;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}

	public int getNote() {
		return this.note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Colocataire getColocataire() {
		return this.colocataire;
	}

	public void setColocataire(Colocataire colocataire) {
		this.colocataire = colocataire;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Proprietaire getProprietaire() {
		return this.proprietaire;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}

}