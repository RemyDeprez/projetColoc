package fr.formation.afpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.afpa.domain.Location;

@Repository
public interface ILocationDao extends JpaRepository<Location, Integer> {
	
	public List<Location> findBymaxColocataireLessThanEqualAndLoyerLessThanEqualAndSuperficeLessThanEqual(Integer maxColocataire, Double loyer, Integer superfice);
}
