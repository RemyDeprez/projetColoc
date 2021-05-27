package fr.formation.afpa.service;

import java.util.List;

import fr.formation.afpa.domain.Utilisateur;

public interface IUtilisateurService {

	List<Utilisateur> findAll();

	void saveOrUpdate(Utilisateur u);

	void delete(Utilisateur u);

	void deleteById(Integer id);
}
