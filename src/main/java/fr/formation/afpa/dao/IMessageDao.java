package fr.formation.afpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import fr.formation.afpa.domain.Messages;




public interface IMessageDao extends JpaRepository<Messages, Integer> {
	
	 List<Messages> findByChannelIDLike(Integer channelID);

}
