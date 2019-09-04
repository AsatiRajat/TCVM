package org.yash.tcvm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.yash.tcvm.container.handler.ContainerManager;
import org.yash.tcvm.enums.Coffee;
import org.yash.tcvm.enums.DrinksType;
import org.yash.tcvm.exception.MaterialNotSufficientException;
import org.yash.tcvm.service.CoffeeService;

public class CoffeeServiceTest {

	private CoffeeService coffeeService = new CoffeeService();

	private ContainerManager containerManager = ContainerManager.getInstance();

	@Test
	public void shouldReturnTrueWhenTeaCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true, containerManager.getTeaCapacity() >= Coffee.COFFEE.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, coffeeService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenTeaCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(false,
				containerManager.getTeaCapacity() >= Coffee.COFFEE.getConsumptionAndWasteMaterialTotal() * 500);
		assertEquals(false, coffeeService.isMaterialSufficient(500));
	}

	@Test
	public void shouldReturnTrueWhenWaterCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true, containerManager.getWaterCapacity() >= Coffee.WATER.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, coffeeService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenWaterCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		int intialCoffeeCapacity = containerManager.getCoffeeCapacity();
		containerManager.setCoffeeCapacity(5000);
		assertEquals(false,
				containerManager.getWaterCapacity() >= Coffee.WATER.getConsumptionAndWasteMaterialTotal() * 1000);
		assertEquals(false, coffeeService.isMaterialSufficient(1000));
		containerManager.setTeaCapacity(intialCoffeeCapacity);
	}

	@Test
	public void shouldReturnTrueWhenMilkCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true, containerManager.getMilkCapacity() >= Coffee.MILK.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, coffeeService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenMilkCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		int intialCoffeeCapacity = containerManager.getCoffeeCapacity();
		containerManager.setCoffeeCapacity(5000);
		int intialWaterCapacity = containerManager.getWaterCapacity();
		containerManager.setWaterCapacity(12000);
		assertEquals(false,
				containerManager.getMilkCapacity() >= Coffee.MILK.getConsumptionAndWasteMaterialTotal() * 500);
		assertEquals(false, coffeeService.isMaterialSufficient(500));
		containerManager.setTeaCapacity(intialCoffeeCapacity);
		containerManager.setWaterCapacity(intialWaterCapacity);

	}

	@Test
	public void shouldReturnTrueWhenSugarCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true, containerManager.getSugarCapacity() >= Coffee.SUGAR.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, coffeeService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenSugarCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		int intialCoffeeCapacity = containerManager.getCoffeeCapacity();
		containerManager.setCoffeeCapacity(5000);
		int intialWaterCapacity = containerManager.getWaterCapacity();
		containerManager.setWaterCapacity(12000);
		int intialMilkCapacity = containerManager.getMilkCapacity();
		containerManager.setMilkCapacity(45000);
		assertEquals(false,
				containerManager.getSugarCapacity() >= Coffee.SUGAR.getConsumptionAndWasteMaterialTotal() * 500);
		assertEquals(false, coffeeService.isMaterialSufficient(500));
		containerManager.setTeaCapacity(intialCoffeeCapacity);
		containerManager.setWaterCapacity(intialWaterCapacity);
		containerManager.setMilkCapacity(intialMilkCapacity);
	}

	@Test
	public void shouldReturnTrueWhenMaterialIsAvailable() {
		coffeeService.makeDrink(DrinksType.COFFEE.name(), 5);
		assertEquals(true, coffeeService.isMaterialSufficient(5));
	}

	@Test(expected = MaterialNotSufficientException.class)
	public void shouldThrowExceptionIfMaterialIsNotAvailable() {
		coffeeService.makeDrink(DrinksType.COFFEE.name(), 500);
	}

}
