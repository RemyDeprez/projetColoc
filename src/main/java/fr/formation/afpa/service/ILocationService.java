package fr.formation.afpa.service;

import java.util.List;
import java.util.Optional;

import fr.formation.afpa.domain.Location;

public interface ILocationService {

	List<Location> findAll();

	void saveOrUpdate(Location u);

	void delete(Location u);

	void deleteById(Integer id);

	Optional<Location> findById(Integer id);
	
	public List<Location> findBymaxColocataireLessThanEqualAndLoyerLessThanEqualAndSuperficeLessThanEqual(Integer maxColocataire, Integer loyer, Integer superfice);
}
