package org.ttn.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.ttn.domain.Order;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @InjectMocks
    EmailService emailService1;

    EmailService emailService = EmailService.getInstance();

    @Test(expected = RuntimeException.class)
    public void testSendEmailException() {
        Order order = new Order(1, "book", 150);
        emailService1.sendEmail(order);
    }


    @Test
    public void testSendEmail() {
        Order order = new Order(1, "book", 150);

        // check that the method returns true
        assertTrue(emailService.sendEmail(order, "cc"));
    }
}

