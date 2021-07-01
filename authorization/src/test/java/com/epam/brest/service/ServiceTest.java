package com.epam.brest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ServiceTest {

    @Test
    void checkTrueLogin() {
        Service service = new Service();
        Assertions.assertTrue(service.checkLogin("userLogin_123"));
    }

    @Test
    void checkTooShortLogin() {
        Service service = new Service();
        Assertions.assertFalse(service.checkLogin("log"));
    }

    @Test
    void checkTooLongLogin() {
        Service service = new Service();
        Assertions.assertFalse(service.checkLogin("userLogin_123_456_789_000_000_000"));
    }

    @Test
    void checkLoginWithWrongCharacter() {
        Service service = new Service();
        Assertions.assertFalse(service.checkLogin("userLogin-123"));
    }

    @Test
    void checkTruePassword() {
        Service service = new Service();
        Assertions.assertTrue(service.checkPassword("userPassword123"));
    }

    @Test
    void checkTooShortPassword() {
        Service service = new Service();
        Assertions.assertFalse(service.checkPassword("uPass1"));
    }

    @Test
    void checkTooLongPassword() {
        Service service = new Service();
        Assertions.assertFalse(service.checkPassword("userPassword1234567890000000000"));
    }

    @Test
    void checkPasswordWithWrongCharacter() {
        Service service = new Service();
        Assertions.assertFalse(service.checkPassword("userPassword123+"));
    }

}