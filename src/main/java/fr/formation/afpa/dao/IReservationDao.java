package fr.formation.afpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.afpa.domain.Reservation;

public interface IReservationDao extends JpaRepository<Reservation, Integer> {

}
