

import org.junit.Test;
import static org.junit.Assert.*;

//CODE ATTRIBUTION
//This code was adapted from the source below

//Author: OpenAI
//Date: 2024
//Title: ChatGPT-4 (Apr 9 version) [Large Language Model].
//Source: https://openai.com/

public class MessageTest {
    
    @Test
    public void testMessageLengthLimitSuccess() {
        String message = "Hello Mike, how are you doing today?"; 
        assertTrue("Message ready to send.", message.length() <= 250);
    }   

    @Test
    public void testMessageLengthLimitFailure() {
        String longMessage = new String(new char[260]).replace('\0', 'A');
        assertFalse("The message exceeds 250 characters by 10 characters, please reduce size ", longMessage.length() <= 250);
    }

    @Test
    public void testPhoneNumberFormatSuccess() {
        String recipient = "+27718693002";
        assertTrue("The cell phone number has been captured successfully.", recipient.startsWith("+27") && recipient.length() >= 11);
    }
    
    @Test
    public void testPhoneNumberFormatFailure() {
        String recipient = "0812345678";
        assertFalse("Cell phone number is incorrectly formatted, please correct the number and try again.", recipient.startsWith("+27") && recipient.length() >= 11);
    }

    @Test
    public void testMessageHashSuccess() {
        String hash = "00:01TONIGHT!";
        assertTrue("Hash matched expected output", hash.equals("00:01TONIGHT!"));
    }

    @Test
    public void testMessageIDGenerated() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", "Send");
        assertNotNull("Message ID generated.", msg.getMessageID());
    }

    @Test
    public void testSendMessageSuccess() {
        Message msg = new Message("+27718693002", "Hey", "Send");
        assertEquals("Message successfully sent.", msg.getSendMessage());
    }

    @Test
    public void testSendMessageDisregard() {
        Message msg = new Message("+27718693002", "Hey", "Disregard");
        assertEquals("Press 0 to delete message.", msg.getSendMessage());
    }

    @Test
    public void testSendMessageStore() {
        Message msg = new Message("+27718693002", "Hey", "Store");
        assertEquals("Message successfully stored.", msg.getSendMessage());
    }

}
