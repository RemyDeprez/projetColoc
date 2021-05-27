package fr.formation.afpa.service;

import java.util.List;

import fr.formation.afpa.domain.Evaluation;

public interface IEvaluationService {

	List<Evaluation> findAll();

	void saveOrUpdate(Evaluation u);

	void delete(Evaluation u);

	void deleteById(Integer id);
}
