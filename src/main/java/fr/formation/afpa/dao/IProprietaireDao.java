package fr.formation.afpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.afpa.domain.Proprietaire;

public interface IProprietaireDao extends JpaRepository<Proprietaire, Integer> {

}
