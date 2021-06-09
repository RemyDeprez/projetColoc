package fr.formation.afpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.afpa.dao.IChannelDao;
import fr.formation.afpa.domain.Channel;

@Service
@Transactional
public class ChannelService implements IChannelService {
	
	@Autowired
	IChannelDao dao;
	
	public IChannelDao getDao() {
		return dao;
	}

	@Override
	public List<Channel> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	@Override
	public Optional<Channel> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void saveOrUpdate(Channel u) {
		System.out.println("in the save channel");
		dao.save(u);
		
	}

	@Override
	public void delete(Channel u) {
		dao.delete(u);
		
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

}
