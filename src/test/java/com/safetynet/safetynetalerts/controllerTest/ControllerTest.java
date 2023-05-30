package com.safetynet.safetynetalerts.controllerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.safetynet.safetynetalerts.controller.Controller;

class ControllerTest {

	private Controller controller = new Controller();

	@Test
	void equalsTest() {
		Controller controller2 = new Controller();
		Controller controller3 = new Controller();
		assertTrue(controller2.equals(controller3));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(controller.equals(controller));
	}

	@Test
	void equalsNullObjetTest() {
		Controller controllerNull = null;
		assertFalse(controller.equals(controllerNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(controller.equals(objetDifferent));
	}

}
