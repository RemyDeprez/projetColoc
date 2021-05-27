package fr.formation.afpa.service;

import java.util.List;

import fr.formation.afpa.domain.Proprietaire;

public interface IProprietaireService {

	List<Proprietaire> findAll();

	void saveOrUpdate(Proprietaire u);

	void delete(Proprietaire u);

	void deleteById(Integer id);
}
