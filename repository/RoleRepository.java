package fr.formation.afpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.afpa.domain.AppUser;

public interface RoleRepository extends JpaRepository<AppUser, Integer> {

	@Query("Select ur.appRole.roleName from UserRole ur where ur.appUser.userId = ?1")
	List<String> getRoleNames(Long userId);
	
}
