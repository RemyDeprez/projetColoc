package fr.formation.afpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.afpa.dao.IProprietaireDao;
import fr.formation.afpa.domain.Proprietaire;

@Service
@Transactional
public class ProprietaireService implements IProprietaireService {

	@Autowired
	IProprietaireDao dao;
	
		
	public IProprietaireDao getDao() {
		return dao;
	}

	@Override
	public List<Proprietaire> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveOrUpdate(Proprietaire u) {
		dao.save(u);
	}

	@Override
	public void delete(Proprietaire u) {
		dao.delete(u);
		
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

}
