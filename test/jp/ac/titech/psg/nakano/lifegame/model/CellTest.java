package jp.ac.titech.psg.nakano.lifegame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {

	@Test
	public void test() {
		Cell c = new Cell();
		assertFalse(c.isAlive());
		c.changeStatus();
		assertTrue(c.isAlive());
		
		c.adaptRule(0);
		c.update();
		assertFalse(c.isAlive());
		
		c.adaptRule(8);
		c.update();
		assertFalse(c.isAlive());
		
		c.adaptRule(2);
		c.update();
		assertFalse(c.isAlive());
		
		c.adaptRule(3);
		c.update();
		assertTrue(c.isAlive());
	}

}
