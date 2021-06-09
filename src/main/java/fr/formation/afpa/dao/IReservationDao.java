package fr.formation.afpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.afpa.domain.Reservation;

public interface IReservationDao extends JpaRepository<Reservation, Integer> {

	public List<Reservation> findByColocataireUserIdLike(Integer userId);
	
}
