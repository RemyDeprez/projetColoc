package fr.formation.afpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Messages {
	
	 	@Id
	    @GeneratedValue
	    @Column(name = "idmessages", nullable = false)
		private int idmessages;
	    
	    @Column(name="channelID", nullable=false)
		private int channelID;
	    
	    @Column(name="content", nullable=false)
		private String content;
	    
	    @Column(name="sender", nullable=false)
	 		private String sender;

		public Messages() {
			
		}

		public int getIdmessages() {
			return idmessages;
		}

		public void setIdmessages(int idmessages) {
			this.idmessages = idmessages;
		}

		public int getChannelID() {
			return channelID;
		}

		public void setChannelID(int channelID) {
			this.channelID = channelID;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getSender() {
			return sender;
		}

		public void setSender(String sender) {
			this.sender = sender;
		}
		
		
	    
	    
}
