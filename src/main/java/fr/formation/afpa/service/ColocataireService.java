package fr.formation.afpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.afpa.dao.IColocataireDao;
import fr.formation.afpa.domain.Colocataire;

@Service
@Transactional
public class ColocataireService implements IColocataireService {

	@Autowired
	IColocataireDao dao;
	
		
	public IColocataireDao getDao() {
		return dao;
	}

	@Override
	public List<Colocataire> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveOrUpdate(Colocataire u) {
		dao.save(u);
	}

	@Override
	public void delete(Colocataire u) {
		dao.delete(u);
		
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

}
