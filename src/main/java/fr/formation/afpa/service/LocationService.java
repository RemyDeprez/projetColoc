package fr.formation.afpa.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.afpa.dao.ILocationDao;
import fr.formation.afpa.domain.Location;

@Service
@Transactional
public class LocationService implements ILocationService {

	@Autowired
	ILocationDao dao;

	public ILocationDao getDao() {
		return dao;
	}

	@Override
	public List<Location> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveOrUpdate(Location u) {
		dao.save(u);
	}

	@Override
	public void delete(Location u) {
		dao.delete(u);

	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public Optional<Location> findById(Integer id) {
		return dao.findById(id);

	}
	


	public void savePhoto(MultipartFile photo) throws Exception {
		String folder ="/images";
		byte[] bytes = photo.getBytes();
		Path path = Paths.get(folder + photo.getOriginalFilename());
		Files.write(path, bytes);
		
	}

}
