package org.ttn.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.ttn.domain.Order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    EmailService emailService;

    @InjectMocks
    OrderService orderService;

    @Test
    public void testPlaceOrder() {
        Order order = new Order(1, "book", 150);

        orderService.placeOrder(order);

        // check the expected method is invoked by email service
        verify(emailService, times(1)).sendEmail(order);

        // check that price with tax is populated
        assertEquals(180d, order.getPriceWithTax(), 0.001);

        // check that order notified property is true
        assertTrue(order.isCustomerNotified());
    }

    @Test
    public void testPlaceOrderReturnsTrue() {

        Order order = new Order(1, "book", 150);
        when(emailService.sendEmail(order, "cc")).thenReturn(true);

        boolean response = orderService.placeOrder(order, "cc");

        // check that the method returns true
        assertTrue(response);

        // check the expected method is invoked by email service
        verify(emailService, times(1)).sendEmail(order, "cc");

        // check that price with tax is populated
        assertEquals(180d, order.getPriceWithTax(), 0.001);

        // check that customer notify property is true
        assertTrue(order.isCustomerNotified());
    }

}

