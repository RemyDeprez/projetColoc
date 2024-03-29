package fr.formation.afpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.afpa.dao.IUtilisateurDao;
import fr.formation.afpa.domain.AppUser;


@Service
@Transactional
public class UtilisateurService implements IUtilisateurService {

	@Autowired
	IUtilisateurDao dao;
   
		
	public IUtilisateurDao getDao() {
		return dao;
	}

	@Override
	public List<AppUser> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveOrUpdate(AppUser u) {
		dao.save(u);
	}

	@Override
	public void delete(AppUser u) {
		dao.delete(u);
		
	}

	@Override
	public void deleteByUserId(int id) {
		dao.deleteByUserId(id);
		
	}
	@Override
	public AppUser findByLogin(String login) {
		
		return dao.findByUserName(login);
	}

	@Override
	public AppUser findByUserId(int userId) {
		
		return dao.findByUserId(userId);
	}
}


