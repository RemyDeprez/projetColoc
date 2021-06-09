package fr.formation.afpa.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * The persistent class for the reservation database table.
 * 
 */
@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reservationID;

	private byte isPermutable;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="LocationID")
	private Location location;

	@Override
	public String toString() {
		return "Reservation [reservationID=" + reservationID + ", isPermutable=" + isPermutable + ", location="
				+ location + ", colocataire=" + colocataire + "]";
	}

	//bi-directional many-to-one association to Colocataire
	@ManyToOne
	@JoinColumn(name="utilisateur_id")
	private AppUser colocataire;
	
	@JoinColumn(name="statut")
	private byte statut;

	public Reservation() {
	}

	public int getReservationID() {
		return this.reservationID;
	}

	
	public byte getStatut() {
		return statut;
	}

	public void setStatut(byte statut) {
		this.statut = statut;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public byte getIsPermutable() {
		return this.isPermutable;
	}

	public void setIsPermutable(byte isPermutable) {
		this.isPermutable = isPermutable;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public AppUser getColocataire() {
		return this.colocataire;
	}

	public void setColocataire(AppUser colocataire) {
		this.colocataire = colocataire;
	}

}