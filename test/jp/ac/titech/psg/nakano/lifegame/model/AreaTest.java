package jp.ac.titech.psg.nakano.lifegame.model;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static jp.ac.titech.psg.nakano.lifegame.Const.*;

import org.junit.Test;

public class AreaTest {

	@Test
	public void test(){
		Area area = new Area();
		assertFalse(area.isAlive(0, 0));
		area.changeStatus(0, 0);
		assertTrue(area.isAlive(0, 0));
		
		area.update();
		assertFalse(area.isAlive(0, 0));
		
		area.changeStatus(0, 0);
		area.changeStatus(CELL_RAW-1, CELL_COLUMN-1);
		area.reset();
		assertFalse(area.isAlive(0, 0));
		assertFalse(area.isAlive(CELL_RAW-1, CELL_COLUMN -1));
	}

	@Test
	public void privateTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Area area = new Area();
		Method method = Area.class.getDeclaredMethod("getNumOfAroundAliveCells", int.class, int.class);
		method.setAccessible(true);
		area.changeStatus(0, 1);
		int actual = (int) method.invoke(area, 0, 0);
		assertEquals(1, actual);
		area.changeStatus(1, 0);
		actual = (int) method.invoke(area, 0,0);
		assertEquals(2, actual);
		area.changeStatus(1, 1);
		actual = (int) method.invoke(area, 0, 0);
		assertEquals(3, actual);
		
		area.changeStatus(CELL_RAW-1, CELL_COLUMN-2);
		actual = (int) method.invoke(area, CELL_RAW-1, CELL_COLUMN-1);
		assertEquals(1, actual);
	}

}
