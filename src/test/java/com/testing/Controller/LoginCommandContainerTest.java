package com.testing.Controller;

import com.testing.Controller.Login;
import com.testing.Controller.LoginCommandContainer;
import com.testing.Controller.Register;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginCommandContainerTest {
    @Test
    void testGetCommand() {
        assertNull(LoginCommandContainer.getCommand("Name"));
        assertTrue(LoginCommandContainer.getCommand((String) "login") instanceof Login);
        assertTrue(LoginCommandContainer.getCommand((String) "register") instanceof Register);
    }
}

