package fr.formation.afpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.afpa.domain.Colocataire;

public interface IColocataireDao extends JpaRepository<Colocataire, Integer> {

}
