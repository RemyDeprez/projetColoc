package fr.formation.afpa.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class ChatMessage {

	 @Id
	   private String id;
	   private String chatId;
	   private String senderId;
	   private String recipientId;
	   private String senderName;
	   private String recipientName;
	   private String content;
	   private Date timestamp;
	   private MessageStatus status;
	   
	   public enum MessageStatus {
		    RECEIVED, DELIVERED
		}

	}

