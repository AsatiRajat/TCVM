package org.yash.tcvm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.yash.tcvm.container.handler.ContainerManager;
import org.yash.tcvm.enums.DrinksType;
import org.yash.tcvm.enums.Tea;
import org.yash.tcvm.exception.MaterialNotSufficientException;
import org.yash.tcvm.service.TeaService;

public class TeaServiceTest {

	private TeaService teaService = new TeaService();

	private ContainerManager containerManager = ContainerManager.getInstance();

	@Test
	public void shouldReturnTrueWhenTeaCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true, containerManager.getTeaCapacity() >= Tea.TEA.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, teaService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenTeaCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(false, containerManager.getTeaCapacity() >= Tea.TEA.getConsumptionAndWasteMaterialTotal() * 500);
		assertEquals(false, teaService.isMaterialSufficient(500));
	}

	@Test
	public void shouldReturnTrueWhenWaterCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true, containerManager.getWaterCapacity() >= Tea.WATER.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, teaService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenWaterCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		int intialTeaCapacity = containerManager.getTeaCapacity();
		containerManager.setTeaCapacity(5000);
		assertEquals(false, containerManager.getWaterCapacity() >= Tea.WATER.getConsumptionAndWasteMaterialTotal() * 500);
		assertEquals(false, teaService.isMaterialSufficient(500));
		containerManager.setTeaCapacity(intialTeaCapacity);
	}

	@Test
	public void shouldReturnTrueWhenMilkCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true, containerManager.getMilkCapacity() >= Tea.MILK.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, teaService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenMilkCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		int intialTeaCapacity = containerManager.getTeaCapacity();
		containerManager.setTeaCapacity(5000);
		int intialWaterCapacity = containerManager.getWaterCapacity();
		containerManager.setWaterCapacity(33000);
		boolean actual = containerManager.getMilkCapacity() >= Tea.MILK.getConsumptionAndWasteMaterialTotal() * 500;
		assertEquals(false, actual);
		assertEquals(false, teaService.isMaterialSufficient(500));
		containerManager.setTeaCapacity(intialTeaCapacity);
		containerManager.setWaterCapacity(intialWaterCapacity);
	}

	@Test
	public void shouldReturnTrueWhenSugarCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true, containerManager.getSugarCapacity() >= Tea.SUGAR.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, teaService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenSugarCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		int intialTeaCapacity = containerManager.getTeaCapacity();
		containerManager.setTeaCapacity(5000);
		int intialWaterCapacity = containerManager.getWaterCapacity();
		containerManager.setWaterCapacity(33000);
		int intialMilkCapacity = containerManager.getMilkCapacity();
		containerManager.setMilkCapacity(23000);
		assertEquals(false, containerManager.getSugarCapacity() >= Tea.SUGAR.getConsumptionAndWasteMaterialTotal() * 500);
		assertEquals(false, teaService.isMaterialSufficient(500));
		containerManager.setTeaCapacity(intialTeaCapacity);
		containerManager.setWaterCapacity(intialWaterCapacity);
		containerManager.setMilkCapacity(intialMilkCapacity);
	}

	@Test
	public void shouldReturnTrueWhenMaterialIsAvailable() {
		teaService.makeDrink(DrinksType.TEA.name(), 5);
		assertEquals(true, teaService.isMaterialSufficient(5));
	}

	@Test(expected = MaterialNotSufficientException.class)
	public void shouldThrowExceptionWhenMaterialIsNotAvailable() {
		teaService.makeDrink(DrinksType.TEA.name(), 500);
	}

}
