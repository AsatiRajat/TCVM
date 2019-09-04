package org.yash.tcvm;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.yash.tcvm.container.handler.ContainerManager;
import org.yash.tcvm.enums.DrinksType;
import org.yash.tcvm.factory.DrinkServiceFactory;
import org.yash.tcvm.service.interfaces.DrinkService;
import org.yash.tcvm.utils.CustomScanner;

@RunWith(MockitoJUnitRunner.class)
public class ContainerManagerTest {

	@InjectMocks
	ContainerManager containerManager;

	@Mock
	DrinkService drinkService;

	@Mock
	DrinkServiceFactory drinkServiceFactory;

	@Mock
	CustomScanner scanner;

	@Mock
	private Logger logger;

	@Test
	public void checkIntialCapacityOfVendingMachineContainer() {

		containerManager.intializedContainer();

		assertEquals(2000, containerManager.getTeaCapacity());
		assertEquals(2000, containerManager.getCoffeeCapacity());
		assertEquals(8000, containerManager.getSugarCapacity());
		assertEquals(15000, containerManager.getWaterCapacity());
		assertEquals(10000, containerManager.getMilkCapacity());

	}

	@Test
	public void containerShouldBeResetWithIntialCapacityOfContainers() {
		containerManager.resetContainer();
	}

	@Test
	public void shouldReturnTotalDrinksSale() {

		containerManager.getDrinkSaleReportMap().put(DrinksType.TEA.name(), 2);
		containerManager.getDrinkSaleReportMap().put(DrinksType.BLACK_TEA.name(), 2);
		containerManager.getDrinkSaleReportMap().put(DrinksType.COFFEE.name(), 2);
		containerManager.getDrinkSaleReportMap().put(DrinksType.BLACK_COFFEE.name(), 2);

		containerManager.checkTotalSale();
	}

	@Test
	public void shouldReturnContainerStatus() {
		containerManager.containerStatus();
	}

	@Test
	public void shouldReturnCalculateTotalWastedMaterial() {

//		Map<String, Integer> saleMap = new HashMap<String, Integer>();
//		saleMap.put(DrinksType.TEA.name(), 2);
//		saleMap.put(DrinksType.BLACK_TEA.name(), 2);
//		saleMap.put(DrinksType.COFFEE.name(), 2);
//		saleMap.put(DrinksType.BLACK_COFFEE.name(), 2);
		
		

		containerManager.calculateTotalWastedMaterial(containerManager.getDrinkSaleReportMap());

	}

	@Test
	public void shouldRefillTeaContainer() {
		int actual = containerManager.refillContainer(1);
		assertEquals(1, actual);
	}

	@Test
	public void shouldRefillCoffeeContainer() {
		int actual = containerManager.refillContainer(2);
		assertEquals(1, actual);
	}

	@Test
	public void shouldRefillSugarContainer() {
		int actual = containerManager.refillContainer(3);
		assertEquals(1, actual);
	}

	@Test
	public void shouldRefillWaterContainer() {
		int actual = containerManager.refillContainer(4);
		assertEquals(1, actual);
	}

	@Test
	public void shouldRefillMilkContainer() {
		int actual = containerManager.refillContainer(5);
		assertEquals(1, actual);
	}

	@Test
	public void shouldDisplayMessageForInValidChoiceForRefillContainers() {
		int actual = containerManager.refillContainer(6);
		assertEquals(0, actual);
	}

}
