package org.yash.tcvm;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.yash.tcvm.factory.DrinkServiceFactory;
import org.yash.tcvm.service.BlackCoffeeService;
import org.yash.tcvm.service.BlackTeaService;
import org.yash.tcvm.service.CoffeeService;
import org.yash.tcvm.service.TeaService;
import org.yash.tcvm.service.interfaces.DrinkService;

@RunWith(MockitoJUnitRunner.class)
public class DrinkServiceFactoryTest {

	@InjectMocks
	DrinkServiceFactory drinkServiceFactory;

	@Mock
	TeaService teaService;

	@Mock
	BlackTeaService blackTeaService;

	@Mock
	CoffeeService coffeeService;

	@Mock
	BlackCoffeeService blackCoffeeService;

	@Test
	public void shouldReturnTeaObject() {

		DrinkService mockDrinkService = new TeaService();

		DrinkService actual = drinkServiceFactory.getDrinkServiceInstance(1);

		assertNotSame(mockDrinkService, actual);

	}

	@Test
	public void shouldReturnBlackTeaObject() {

		DrinkService mockDrinkService = new BlackTeaService();

		DrinkService actual = drinkServiceFactory.getDrinkServiceInstance(2);

		assertNotSame(mockDrinkService, actual);

	}

	@Test
	public void shouldReturnCoffeeObject() {

		DrinkService mockDrinkService = new CoffeeService();

		DrinkService actual = drinkServiceFactory.getDrinkServiceInstance(3);

		assertNotSame(mockDrinkService, actual);

	}

	@Test
	public void shouldReturnBlackCoffeeObject() {

		DrinkService mockDrinkService = new BlackCoffeeService();

		DrinkService actual = drinkServiceFactory.getDrinkServiceInstance(4);

		assertNotSame(mockDrinkService, actual);

	}

	@Test
	public void shouldReturnNullForDefaultCase() {

		DrinkService actual = drinkServiceFactory.getDrinkServiceInstance(5);

		assertNull(actual);

	}

}
