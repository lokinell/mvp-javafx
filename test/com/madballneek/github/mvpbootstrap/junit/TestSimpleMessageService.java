package com.madballneek.github.mvpbootstrap.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.madballneek.github.mvpboostrap.model.request.MessageRequestData;
import com.madballneek.github.mvpboostrap.model.response.MessageResponseData;
import com.madballneek.github.mvpboostrap.service.message.SimpleMessageServiceImpl;

public class TestSimpleMessageService {
	SimpleMessageServiceImpl messageService;

	@Before
	public void setUp() throws Exception {
		messageService = new SimpleMessageServiceImpl();
	}

	@Test
	public void testSimpleMessage() throws Exception {
		// These are all such frivolous tests, but demonstarting JUnit.
		String name = "Jimmy";
		String expectedMessage = "Hey there, " + name;
		MessageResponseData actualMessage = messageService.sayHello(new MessageRequestData(name));
		assertEquals(expectedMessage, actualMessage.message);

		expectedMessage = "See yea, " + name;
		actualMessage = messageService.sayGoodbye(new MessageRequestData(name));
		assertEquals(expectedMessage, actualMessage.message);
	}

	@After
	public void tearDown() throws Exception {
		messageService = null;
	}
}
