package fr.formation.afpa.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proprietaire database table.
 * 
 */
@Entity
@Table(name="proprietaire")
public class Proprietaire {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int utilisateurID;

	private int idLocation;

	private int nbrBientotal;

	//bi-directional one-to-one association to Evaluation
	@OneToOne(mappedBy="proprietaire")
	private Evaluation evaluation;

	//bi-directional many-to-one association to Location
	@OneToMany(mappedBy="proprietaire")
	private List<Location> locations;

	//bi-directional one-to-one association to Utilisateur
	@OneToOne(mappedBy="proprietaire")
	private Utilisateur utilisateur;

	public Proprietaire() {
	}

	public int getUtilisateurID() {
		return this.utilisateurID;
	}

	public void setUtilisateurID(int utilisateurID) {
		this.utilisateurID = utilisateurID;
	}

	public int getIdLocation() {
		return this.idLocation;
	}

	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}

	public int getNbrBientotal() {
		return this.nbrBientotal;
	}

	public void setNbrBientotal(int nbrBientotal) {
		this.nbrBientotal = nbrBientotal;
	}

	public Evaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public List<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Location addLocation(Location location) {
		getLocations().add(location);

		return location;
	}

	public Location removeLocation(Location location) {
		getLocations().remove(location);
		location.setProprietaire(null);

		return location;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}