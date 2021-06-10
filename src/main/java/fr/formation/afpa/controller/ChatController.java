package fr.formation.afpa.controller;

import java.security.Principal;

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

@Controller
public class ChatController {
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private UserRepository utilisateurService;
	
	@RequestMapping(path = "/getMP/{userId}", method  = RequestMethod.GET)
	public String getPrivateMessage(Model model, Principal principal, @PathVariable("userId") int userId) {
		String loginedUser = principal.getName();
		AppUser actualUser = utilisateurService.findByUserName(loginedUser);
		
		String sendId = Integer.toString(actualUser.getUserId());
		String receiveId = Integer.toString(userId);
		String chanIdString = sendId+receiveId;
		int chanId = Integer.parseInt(chanIdString);
		
		if(!channelService.findById(chanId).isPresent()) {
			Channel channel = new Channel();
			channel.setChannelID(chanId);
			channel.setReceiverID(userId);
			channel.setSenderID(actualUser.getUserId());
			channelService.saveOrUpdate(channel);
		}
		System.out.println(chanId);
		model.addAttribute("receiver", userId);
		return "channel";
		
	}

}
