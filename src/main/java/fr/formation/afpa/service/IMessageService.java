package fr.formation.afpa.service;

import java.util.List;

import fr.formation.afpa.domain.Messages;




public interface IMessageService {
	List<Messages> findAll();

	void saveOrUpdate(Messages u);

	void delete(Messages u);

	void deleteById(Integer id);
	
	 List<Messages> findByChannelIDLike(Integer channelID);
}
