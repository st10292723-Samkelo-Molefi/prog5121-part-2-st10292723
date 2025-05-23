import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;



//The location of the JSON file where messages will be kept
public class Message {
    private final String FILE_PATH = "message.json";
    private String messageID;
    private String recipientCell;
    private String messageContent;
    private String messageStatus;
    
    public Message(String recipientCell, String messageContent, String messageStatus) {
        this.messageID = UUID.randomUUID().toString();
        this.recipientCell = recipientCell;
        this.messageContent = messageContent;
        this.messageStatus = messageStatus;
    }
    
//Constructor that sets up the file for storing messages.   
    public Message() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                writer.write("[]"); 
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "There was a problem starting the message file.");
            }
        }
    }

//CODE ATTRIBUTION
//This code was adapted from the source below

//Author: OpenAI
//Date: 2024
//Title: ChatGPT-4 (Apr 9 version) [Large Language Model].
//Source: https://openai.com/
    
//Sends a message and saves it in the JSON file once it checks that everything is correct. 
    public String sendMessage(String messageId, String recipientCell, String messageContent) {
        if (!recipientCell.matches("^\\+\\d{10,13}$")) {
            return "Invalid cellphone number. Format must be [+] followed by digits.";
        } 
        
        if (messageContent.length() < 50 || messageContent.length() > 250) {
            return "A message should have a length of at least 50 characters but no more than 250 characters.";
        }    
        
        JSONObject message = new JSONObject();
        message.put("id", messageId);
        message.put("recipient", recipientCell);
        message.put("content", messageContent);

        JSONArray messages = readMessages();
        messages.put(message);

        return writeMessages(messages) ? "Message sent" : "Failed to save message.";
    }    

//Shows all the saved messages in a way that's easy to read, or it gives a standard message if there aren't any messages.
    public String printMessages() {
        JSONArray messages = readMessages();
        if (messages.length() == 0) {
            return "No messages have been sent so far.";
        }        
     
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < messages.length(); i++) {
        JSONObject msg = messages.getJSONObject(i);
        builder.append("ID: ").append(msg.getString("id")).append("\n")
            .append("Recipient: ").append(msg.getString("recipient")).append("\n")
            .append("Message: ").append(msg.getString("content")).append("\n\n");
        }
        
        return builder.toString();
    }      

//Reads and understands all the messages from the JSON file, and if it can't open the file, it gives back an empty list.    
    private JSONArray readMessages() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            return new JSONArray(content);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was a problem reading the messages.");

        }
            return new JSONArray();
    }

//Saves messages to a JSON file (using 4 spaces for each indent), and it gives back false if it can't write the messages.
    private boolean writeMessages(JSONArray messages) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(messages.toString(4)); 
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was a problem saving messages to the file.");
        
        }
            return false;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getSendMessage() {
        switch (messageStatus) {
            case "Send":
                return "Message successfully sent.";
            case "Store":
                return "Message successfully stored.";
            case "Disregard":
                return "Press 0 to delete message.";
            default:
                return "Unknown action.";
        }
    }
}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

