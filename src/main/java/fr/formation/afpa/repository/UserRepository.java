package fr.formation.afpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.afpa.domain.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

	AppUser findByUserName(String userName);
	AppUser findByUserId(Long id);
	

	
}
