package fr.formation.afpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.afpa.domain.AppUser;


public interface IUtilisateurDao extends JpaRepository<AppUser, Integer> {
	
	public AppUser findByUserName(String login);
}
