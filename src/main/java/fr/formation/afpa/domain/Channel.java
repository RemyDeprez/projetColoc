package fr.formation.afpa.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//Classe pour instancier les channels de discussion 

@Entity
@Table(name="channel")
public class Channel {
	
    @Id
    @Column(name = "channelID", nullable = false)
	private int channelID;
    
    @Column(name="receiverID", nullable=false)
	private int receiverID;
    
    @Column(name="senderID", nullable=false)
	private int senderID;
    
    
//	private List<String> messages;
    
	@Column(name="messages")
    private String message;

	public Channel() {
		
	}
	
	
	public int getChannelID() {
		return channelID;
	}
	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}
	public int getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}
	public int getSenderID() {
		return senderID;
	}
	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
//	public List<String> getMessage() {
//		return messages;
//	}
//	public void setMessage(List<String> message) {
//		this.messages = message;
//	}
	
	
	

}
