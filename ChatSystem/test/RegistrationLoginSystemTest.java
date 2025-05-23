
import org.junit.Test;
import static org.junit.Assert.*;


//CODE ATTRIBUTION
//This code was adapted from the source below

//Author: OpenAI
//Date: 2024
//Title: ChatGPT-4 (Apr 9 version) [Large Language Model].
//Source: https://openai.com/

public class RegistrationLoginSystemTest {
    
// Username tests
 @Test
    public void testValidUsername() {
        assertTrue(RegistrationLoginSystem.checkUserName("kyl_1"));
    }

    @Test
    public void testInvalidUsername() {
        assertFalse(RegistrationLoginSystem.checkUserName("kyl!!!!!"));
    }
    
//Password tests
  @Test
    public void testValidPasswordComplexity() {
        assertTrue(RegistrationLoginSystem.checkPasswordComplexity("Ch&&sec@ke99!"));
    }


    @Test
    public void testInvalidPassword() {
        assertFalse(RegistrationLoginSystem.checkPasswordComplexity("password"));
    }

//CellphoneNumber tests
    @Test
    public void testValidCellPhoneNumber() {
        assertTrue(RegistrationLoginSystem.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testInvalidCellPhoneNumber() {
        assertFalse(RegistrationLoginSystem.checkCellPhoneNumber("08966553"));
    }


//Registration tests
    @Test
    public void testRegisterUserSuccess() {
        assertEquals(
            "Successfully captured Username\nSuccessfully captured Password\nSuccessfully added Cellphone Number",
            RegistrationLoginSystem.registerUser(true)
        );
    }

    @Test
    public void testRegisterUserFailure() {
        assertEquals(
            "Registration was unsuccessful. Please ensure that all requirement have been fullfilled.",
            RegistrationLoginSystem.registerUser(false)
        );
    }

    
//Login tests
    @Test
    public void testLoginUsernameSuccess() {
        RegistrationLoginSystem.registerUsername = "kyl_1";
        RegistrationLoginSystem.registerPassword = "Ch&&sec@ke99!";
        assertTrue(RegistrationLoginSystem.loginUsername("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUsernameFailure() {
        RegistrationLoginSystem.registerUsername = "kyl_1";
        RegistrationLoginSystem.registerPassword = "Ch&sec@ke99!";
        assertFalse(RegistrationLoginSystem.loginUsername("wrong_username", "wrong_password"));
    }


    @Test
    public void testReturnLoginStatusSuccess() {
        RegistrationLoginSystem.registerUsername = "kyl_1";
        assertEquals("Welcome kyl_1 it is great to see you!", RegistrationLoginSystem.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatusFailure() {
        assertEquals("Incorrect username or password , please try again.",
                     RegistrationLoginSystem.returnLoginStatus(false));
    }
}
