package fr.formation.afpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.afpa.dao.IEvaluationDao;
import fr.formation.afpa.domain.Evaluation;

@Service
@Transactional
public class EvaluationService implements IEvaluationService {

	@Autowired
	IEvaluationDao dao;
	
		
	public IEvaluationDao getDao() {
		return dao;
	}

	@Override
	public List<Evaluation> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveOrUpdate(Evaluation u) {
		dao.save(u);
	}

	@Override
	public void delete(Evaluation u) {
		dao.delete(u);
		
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

}
