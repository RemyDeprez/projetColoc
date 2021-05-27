package fr.formation.afpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.afpa.domain.Utilisateur;

public interface IUtilisateurDao extends JpaRepository<Utilisateur, Integer> {

}
