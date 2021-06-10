package fr.formation.afpa.service;

import java.util.List;
import java.util.Optional;

import fr.formation.afpa.domain.Location;
import fr.formation.afpa.domain.Reservation;

public interface IReservationService {

	List<Reservation> findAll();

	void saveOrUpdate(Reservation u);

	void delete(Reservation u);

	void deleteById(Integer id);
	
	Optional<Reservation> findById(Integer id);
	
	public List<Reservation> findByColocataireUserIdLike(Integer userId);
	
	public List<Reservation> reservationsProprietaire(List<Location> locations);
}
