package org.yash.tcvm;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.yash.tcvm.container.handler.ContainerManager;
import org.yash.tcvm.enums.DrinksType;
import org.yash.tcvm.exception.InvalidChoiceException;
import org.yash.tcvm.factory.DrinkServiceFactory;
import org.yash.tcvm.service.interfaces.DrinkService;
import org.yash.tcvm.utils.CustomScanner;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineOptionsTest {

	@InjectMocks
	private VendingMachineOptions vendingMachineOptions;

	@Mock
	private ContainerManager containerManager;

	@Mock
	private DrinkService drinkService;

	@Mock
	private DrinkServiceFactory drinkServiceFactory;

	@Mock
	private CustomScanner scanner;

	@Mock
	private Logger logger;

	@Test
	public void shouldRunSwitchBlockForTeaChoiceTest() {

		when(scanner.nextInt()).thenReturn(1, 2);

		when(drinkServiceFactory.getDrinkServiceInstance(1)).thenReturn(drinkService);

		drinkServiceFactory.getDrinkServiceInstance(1);

		verify(drinkServiceFactory).getDrinkServiceInstance(1);

		vendingMachineOptions.vendingMachineOptions(false);

		verify(drinkService).makeDrink(DrinksType.TEA.name(), 2);

	}

	@Test
	public void shouldRunSwitchBlockForBlackTeaChoiceTest() {

		when(scanner.nextInt()).thenReturn(2, 3);

		when(drinkServiceFactory.getDrinkServiceInstance(2)).thenReturn(drinkService);

		drinkServiceFactory.getDrinkServiceInstance(2);

		verify(drinkServiceFactory).getDrinkServiceInstance(2);

		vendingMachineOptions.vendingMachineOptions(false);

		verify(drinkService).makeDrink(DrinksType.BLACK_TEA.name(), 3);

	}

	@Test
	public void shouldRunSwitchBlockForCoffeeChoiceTest() {

		when(scanner.nextInt()).thenReturn(3, 4);

		when(drinkServiceFactory.getDrinkServiceInstance(3)).thenReturn(drinkService);

		drinkServiceFactory.getDrinkServiceInstance(3);

		verify(drinkServiceFactory).getDrinkServiceInstance(3);

		vendingMachineOptions.vendingMachineOptions(false);

		verify(drinkService).makeDrink(DrinksType.COFFEE.name(), 4);

	}

	@Test
	public void shouldRunSwitchBlockForBlackCoffeeChoiceTest() {

		when(scanner.nextInt()).thenReturn(4, 5);

		when(drinkServiceFactory.getDrinkServiceInstance(4)).thenReturn(drinkService);

		drinkServiceFactory.getDrinkServiceInstance(4);

		verify(drinkServiceFactory).getDrinkServiceInstance(4);

		vendingMachineOptions.vendingMachineOptions(false);

		verify(drinkService).makeDrink(DrinksType.BLACK_COFFEE.name(), 5);

	}

	@Test
	public void shouldRunSwitchBlockForResetContainerChoiceTest() {

		when(scanner.nextInt()).thenReturn(5);

		doNothing().when(containerManager).resetContainer();

		vendingMachineOptions.vendingMachineOptions(false);

		verify(containerManager).resetContainer();

	}

	@Test
	public void shouldRunSwitchBlockForTotalSellChoiceTest() {

		when(scanner.nextInt()).thenReturn(6);

		doNothing().when(containerManager).checkTotalSale();

		vendingMachineOptions.vendingMachineOptions(false);

		verify(containerManager).checkTotalSale();

	}

	@Test
	public void shouldRunSwitchBlockForContainerStatusChoiceTest() {

		when(scanner.nextInt()).thenReturn(7);

		doNothing().when(containerManager).containerStatus();

		vendingMachineOptions.vendingMachineOptions(false);

		verify(containerManager).containerStatus();

	}

	@Test
	public void shouldRunRefillContainerSwitchCaseTest() {

		when(scanner.nextInt()).thenReturn(8, 2);

		when(containerManager.refillContainer(2)).thenReturn(1);

		vendingMachineOptions.vendingMachineOptions(false);

		verify(containerManager).refillContainer(2);

	}

	@Test
	public void shouldRunRefillContainerSwitchCaseAndCoverWhileConditionTest() {

		when(scanner.nextInt()).thenReturn(8, 0, 1, 0);

		when(containerManager.refillContainer(0)).thenReturn(0);

		vendingMachineOptions.vendingMachineOptions(false);

		verify(logger, times(2)).info("Do you want to continue if yes enter 1 else 0");

	}

	@Test
	public void shouldRunSwitchBlockForExitTcvmChoiceTest() {

		when(scanner.nextInt()).thenReturn(9);

		vendingMachineOptions.vendingMachineOptions(false);

		verify(logger).info("TVCM exiting... Thanks for using TVCM.");

	}

	@Test
	public void shouldRunSwitchBlockForInvalidChoiceTest() {

		when(scanner.nextInt()).thenReturn(10);

		vendingMachineOptions.vendingMachineOptions(false);

		verify(logger).info("Invalid choice, Your selection should be 0 to 9.");

	}

	@Test(expected = InvalidChoiceException.class)
	public void shouldThrowExceptionForStringTest() {
		VendingMachineOptions vendingMachineOptions = new VendingMachineOptions();
		vendingMachineOptions.vendingMachineOptions(false);
	}

}
