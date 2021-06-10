package fr.formation.afpa.service;

import java.util.List;
import java.util.Optional;

import fr.formation.afpa.domain.Channel;



public interface IChannelService {

	List<Channel> findAll();

	void saveOrUpdate(Channel u);

	void delete(Channel u);

	void deleteById(Integer id);

	Optional<Channel> findById(int id);
}
