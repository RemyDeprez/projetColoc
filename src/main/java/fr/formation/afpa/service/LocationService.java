package fr.formation.afpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.afpa.dao.ILocationDao;
import fr.formation.afpa.domain.Location;

@Service
@Transactional
public class LocationService implements ILocationService {

	@Autowired
	ILocationDao dao;
	
		
	public ILocationDao getDao() {
		return dao;
	}

	@Override
	public List<Location> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveOrUpdate(Location u) {
		dao.save(u);
	}

	@Override
	public void delete(Location u) {
		dao.delete(u);
		
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

}