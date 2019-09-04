package org.yash.tcvm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.yash.tcvm.container.handler.ContainerManager;
import org.yash.tcvm.enums.BlackTea;
import org.yash.tcvm.enums.DrinksType;
import org.yash.tcvm.exception.MaterialNotSufficientException;
import org.yash.tcvm.service.BlackTeaService;

public class BlackTeaServiceTest {

	private BlackTeaService blackTeaService = new BlackTeaService();

	private ContainerManager containerManager = ContainerManager.getInstance();

	@Test
	public void shouldReturnTrueWhenTeaCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true,
				containerManager.getTeaCapacity() >= BlackTea.BLACK_TEA.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, blackTeaService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenTeaCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(false,
				containerManager.getTeaCapacity() >= BlackTea.BLACK_TEA.getConsumptionAndWasteMaterialTotal() * 1000);
		assertEquals(false, blackTeaService.isMaterialSufficient(1000));
	}

	@Test
	public void shouldReturnTrueWhenWaterCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true,
				containerManager.getWaterCapacity() >= BlackTea.WATER.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, blackTeaService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenWaterCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		int intialTeaCapacity = containerManager.getTeaCapacity();
		containerManager.setTeaCapacity(2500);
		assertEquals(false,
				containerManager.getWaterCapacity() >= BlackTea.WATER.getConsumptionAndWasteMaterialTotal() * 500);
		assertEquals(false, blackTeaService.isMaterialSufficient(500));
		containerManager.setTeaCapacity(intialTeaCapacity);
	}

	@Test
	public void shouldReturnTrueWhenSugarCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		assertEquals(true,
				containerManager.getSugarCapacity() >= BlackTea.SUGAR.getConsumptionAndWasteMaterialTotal() * 1);
		assertEquals(true, blackTeaService.isMaterialSufficient(1));
	}

	@Test
	public void shouldReturnFalseWhenSugarCapacityIsSufficientInContainer() {
		containerManager.intializedContainer();
		int intialTeaCapacity = containerManager.getTeaCapacity();
		containerManager.setTeaCapacity(2500);
		int intialWaterCapacity = containerManager.getWaterCapacity();
		containerManager.setWaterCapacity(57000);
		assertEquals(false,
				containerManager.getSugarCapacity() >= BlackTea.SUGAR.getConsumptionAndWasteMaterialTotal() * 500);
		assertEquals(false, blackTeaService.isMaterialSufficient(500));
		containerManager.setTeaCapacity(intialTeaCapacity);
		containerManager.setWaterCapacity(intialWaterCapacity);
	}

	@Test
	public void shouldReturnTrueWhenMaterialIsAvailable() {
		blackTeaService.makeDrink(DrinksType.BLACK_TEA.name(), 5);
		assertEquals(true, blackTeaService.isMaterialSufficient(5));
	}

	@Test(expected = MaterialNotSufficientException.class)
	public void shouldThrowExceptionIfMaterialIsNotAvailable() {
		blackTeaService.makeDrink(DrinksType.BLACK_TEA.name(), 500);
	}

}
