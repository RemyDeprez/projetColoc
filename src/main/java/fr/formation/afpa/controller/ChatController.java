package fr.formation.afpa.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.domain.Channel;
import fr.formation.afpa.repository.UserRepository;
import fr.formation.afpa.service.ChannelService;
import fr.formation.afpa.service.UtilisateurService;

@Controller
public class ChatController {
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private UserRepository utilisateurService;
	
	@Autowired
	UtilisateurService userService;
	
	
	//Méthode pour instancier les salons de discussion privés
	@RequestMapping(path = "/getMP/{userId}", method  = RequestMethod.GET)
	public String getPrivateMessage(Model model, Principal principal, @PathVariable("userId") int userId) {
		String loginedUser = principal.getName();
		AppUser actualUser = utilisateurService.findByUserName(loginedUser);
		
		//Scénario pour créer un nouveau salon
		if(channelService.findBySenderIDAndReceiverIDLike(actualUser.getUserId(), userId) == null
			&& channelService.findBySenderIDAndReceiverIDLike(userId, actualUser.getUserId()) == null) {
			Channel newChannel = new Channel();
	
			newChannel.setReceiverID(userId);
			newChannel.setSenderID(actualUser.getUserId());
			channelService.saveOrUpdate(newChannel);
	
	
		AppUser receiver = userService.findByUserId(userId);
		
		model.addAttribute("channelID", newChannel.getChannelID());
		model.addAttribute("receiver", receiver.getUserName());
		return "channel";
		} 
		
		//Scénario pour instancier un salon déjà existant
		AppUser receiver = userService.findByUserId(userId);
		Channel channel = channelService.findBySenderIDAndReceiverIDLike(userId, actualUser.getUserId());
		if(channel == null) {
			channel = channelService.findBySenderIDAndReceiverIDLike(actualUser.getUserId(), userId);
		}
		System.out.println("channel ID chan :" + channel.getChannelID());
		
		model.addAttribute("username", loginedUser);
		model.addAttribute("channelID", channel.getChannelID());
		model.addAttribute("receiver", receiver);
		model.addAttribute("receiverName", receiver.getUserName());
		return "channel";
		
	}

}
