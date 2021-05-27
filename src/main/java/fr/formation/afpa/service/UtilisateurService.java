package fr.formation.afpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.afpa.dao.IUtilisateurDao;
import fr.formation.afpa.domain.Utilisateur;

@Service
@Transactional
public class UtilisateurService implements IUtilisateurService {

	@Autowired
	IUtilisateurDao dao;
	
		
	public IUtilisateurDao getDao() {
		return dao;
	}

	@Override
	public List<Utilisateur> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveOrUpdate(Utilisateur u) {
		dao.save(u);
	}

	@Override
	public void delete(Utilisateur u) {
		dao.delete(u);
		
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

}
