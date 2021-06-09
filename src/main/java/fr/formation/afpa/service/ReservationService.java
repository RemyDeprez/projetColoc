package fr.formation.afpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.afpa.dao.IReservationDao;
import fr.formation.afpa.domain.Reservation;

@Service
@Transactional
public class ReservationService implements IReservationService {

	@Autowired
	IReservationDao dao;
	
		
	public IReservationDao getDao() {
		return dao;
	}

	@Override
	public List<Reservation> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveOrUpdate(Reservation u) {
		dao.save(u);
	}

	@Override
	public void delete(Reservation u) {
		dao.delete(u);
		
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Reservation> findByColocataireUserIdLike(Integer userId) {
		return dao.findByColocataireUserIdLike(userId);
	}

}
