package fr.formation.afpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.afpa.domain.Location;

public interface ILocationDao extends JpaRepository<Location, Integer> {

}
