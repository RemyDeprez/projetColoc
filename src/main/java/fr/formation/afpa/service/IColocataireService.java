package fr.formation.afpa.service;

import java.util.List;

import fr.formation.afpa.domain.Colocataire;

public interface IColocataireService {

	List<Colocataire> findAll();

	void saveOrUpdate(Colocataire u);

	void delete(Colocataire u);

	void deleteById(Integer id);
}
