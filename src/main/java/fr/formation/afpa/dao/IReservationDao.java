package fr.formation.afpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.afpa.domain.Location;
import fr.formation.afpa.domain.Reservation;

public interface IReservationDao extends JpaRepository<Reservation, Integer> {

	public List<Reservation> findByColocataireUserIdLike(Integer userId);
	
	@Query(value = "select r from Reservation r where r.location in :locations")
	public List<Reservation> reservationsProprietaire(@Param("locations") List<Location> locations);
	
}
