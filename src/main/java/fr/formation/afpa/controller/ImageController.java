package fr.formation.afpa.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.afpa.domain.Location;
import fr.formation.afpa.service.LocationService;

@Controller
public class ImageController {
	
	@Autowired
	LocationService service;
	
//	@GetMapping("/getphoto/{locationID}")
//	public void showImage(@PathVariable("locationID") Integer id,
//	                               HttpServletResponse response) throws IOException {
//		System.out.println("in the show image");
//	response.setContentType("image/jpg"); 
//
//	Location loc = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//
//	InputStream is = new ByteArrayInputStream(loc.getPhotos());
//	
//	IOUtils.copy(is, response.getOutputStream());
//	}
//	
//	@PostMapping("/uploadImage")
//	public String uploadImage(@RequestParam("photo") MultipartFile photo) throws Exception{
//		
//		String returnValue=" ";
//		service.savePhoto(photo);
//		return returnValue;
//		}
	

	public static void saveFile(String uploadDir, String fileName,
	        MultipartFile multipartFile) throws IOException {
		System.out.println("In the save path");
	    Path uploadPath = Paths.get(uploadDir);
	     
	    if (!Files.exists(uploadPath)) {
	        Files.createDirectories(uploadPath);
	    }
	     
	    try (InputStream inputStream = multipartFile.getInputStream()) {
	        Path filePath = uploadPath.resolve(fileName);
	        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	    } catch (IOException ioe) {        
	        throw new IOException("Could not save image file: " + fileName, ioe);
	    }     

	}




    
    
    
   
}



