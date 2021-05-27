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
	@OneToMany(mappedBy="reservation")
	private List<Location> locations;

	//bi-directional many-to-one association to Colocataire
	@ManyToOne
	@JoinColumn(name="LocationID")
	private Colocataire colocataire;

	public Reservation() {
	}

	public int getReservationID() {
		return this.reservationID;
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

	public List<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Location addLocation(Location location) {
		getLocations().add(location);
		location.setReservation(this);

		return location;
	}

	public Location removeLocation(Location location) {
		getLocations().remove(location);
		location.setReservation(null);

		return location;
	}

	public Colocataire getColocataire() {
		return this.colocataire;
	}

	public void setColocataire(Colocataire colocataire) {
		this.colocataire = colocataire;
	}

}