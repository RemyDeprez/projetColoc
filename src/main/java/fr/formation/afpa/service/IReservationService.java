package fr.formation.afpa.service;

import java.util.List;

import fr.formation.afpa.domain.Reservation;

public interface IReservationService {

	List<Reservation> findAll();

	void saveOrUpdate(Reservation u);

	void delete(Reservation u);

	void deleteById(Integer id);
}
