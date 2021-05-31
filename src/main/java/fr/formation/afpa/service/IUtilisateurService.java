package fr.formation.afpa.service;

import java.util.List;

import fr.formation.afpa.domain.AppUser;

public interface IUtilisateurService {

	List<AppUser> findAll();

	void saveOrUpdate(AppUser u);

	void delete(AppUser u);

	void deleteById(Integer id);

	AppUser findByLogin(String login);
	
	AppUser findByUserId(Long userId);
}
