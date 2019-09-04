package org.yash.tcvm.factory;

import org.yash.tcvm.service.BlackCoffeeService;
import org.yash.tcvm.service.BlackTeaService;
import org.yash.tcvm.service.CoffeeService;
import org.yash.tcvm.service.TeaService;
import org.yash.tcvm.service.interfaces.DrinkService;

public class DrinkServiceFactory {

	public DrinkService getDrinkServiceInstance(Integer selectedOptionChoice) {

		DrinkService drinkService = null;

		switch (selectedOptionChoice) {
		case 1:
			drinkService = new TeaService();
			break;
		case 2:
			drinkService = new BlackTeaService();
			break;
		case 3:
			drinkService = new CoffeeService();
			break;
		case 4:
			drinkService = new BlackCoffeeService();
			break;
		default:
			break;
		}

		return drinkService;
	}

}
