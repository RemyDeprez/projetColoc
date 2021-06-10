package fr.formation.afpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.afpa.domain.Channel;



public interface IChannelDao extends JpaRepository<Channel, Integer> {

}
