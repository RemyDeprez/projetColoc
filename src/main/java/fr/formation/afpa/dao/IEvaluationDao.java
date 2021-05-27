package fr.formation.afpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.afpa.domain.Evaluation;

public interface IEvaluationDao extends JpaRepository<Evaluation, Integer> {

}
