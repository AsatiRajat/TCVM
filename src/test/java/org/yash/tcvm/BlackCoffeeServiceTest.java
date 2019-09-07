package org.yash.tcvm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.yash.tcvm.container.handler.ContainerManager;
import org.yash.tcvm.enums.BlackCoffee;
import org.yash.tcvm.enums.DrinksType;
import org.yash.tcvm.exception.MaterialNotSufficientException;
import org.yash.tcvm.service.BlackCoffeeService;

public class BlackCoffeeServiceTest {

	private BlackCoffeeService blackCoffeeService = new BlackCoffeeService();

	private ContainerManager containerManager = ContainerManager.getInstance();

	@Test
	public void shouldReturnTrueWhenCoffeeCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true, containerManager
				.getCoffeeCapacity() >= BlackCoffee.COFFEE.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, blackCoffeeService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenCoffeeCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(false, containerManager
				.getCoffeeCapacity() >= BlackCoffee.COFFEE.getConsumptionAndWasteMaterialTotal() * 1000);
		assertEquals(false, blackCoffeeService.isMaterialSufficient(1000));
	}

	@Test
	public void shouldReturnTrueWhenWaterCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true,
				containerManager.getWaterCapacity() >= BlackCoffee.WATER.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, blackCoffeeService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenWaterCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		int intialTeaCapacity = containerManager.getTeaCapacity();
		containerManager.setCoffeeCapacity(2500);
		assertEquals(false,
				containerManager.getWaterCapacity() >= BlackCoffee.WATER.getConsumptionAndWasteMaterialTotal() * 500);
		assertEquals(false, blackCoffeeService.isMaterialSufficient(500));
		containerManager.setTeaCapacity(intialTeaCapacity);
	}

	@Test
	public void shouldReturnTrueWhenSugarCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true,
				containerManager.getSugarCapacity() >= BlackCoffee.SUGAR.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, blackCoffeeService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenSugarCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		int intialCoffeeCapacity = containerManager.getCoffeeCapacity();
		containerManager.setCoffeeCapacity(2500);
		int intialWaterCapacity = containerManager.getWaterCapacity();
		containerManager.setWaterCapacity(57000);
		assertEquals(false,
				containerManager.getSugarCapacity() >= BlackCoffee.SUGAR.getConsumptionAndWasteMaterialTotal() * 500);
		assertEquals(false, blackCoffeeService.isMaterialSufficient(500));
		containerManager.setTeaCapacity(intialCoffeeCapacity);
		containerManager.setWaterCapacity(intialWaterCapacity);
	}

	@Test
	public void shouldReturnTrueWhenMaterialIsAvailable() {
		blackCoffeeService.makeDrink(DrinksType.BLACK_COFFEE.name(), 5);
		assertEquals(true, blackCoffeeService.isMaterialSufficient(5));
	}

	@Test(expected = MaterialNotSufficientException.class)
	public void shouldThrowExceptionIfMaterialIsNotAvailable() {
		blackCoffeeService.makeDrink(DrinksType.BLACK_COFFEE.name(), 500);
	}

}
