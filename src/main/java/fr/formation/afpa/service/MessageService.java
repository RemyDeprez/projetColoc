package fr.formation.afpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import fr.formation.afpa.dao.IMessageDao;
import fr.formation.afpa.domain.Messages;

@Service
@Transactional
public class MessageService implements IMessageService {

	@Autowired
	IMessageDao dao;
	
	public IMessageDao getDao() {
		return dao;
	}


	@Override
	public List<Messages> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void saveOrUpdate(Messages u) {
		dao.save(u);
		
	}

	@Override
	public void delete(Messages u) {
		dao.delete(u);
		
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}
	
public List<Messages> findByChannelIDLike(Integer channelID){
	return dao.findByChannelIDLike(channelID);
}

}
