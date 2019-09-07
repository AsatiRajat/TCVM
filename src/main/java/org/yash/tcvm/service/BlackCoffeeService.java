package org.yash.tcvm.service;

import java.util.function.IntPredicate;
import java.util.logging.Logger;

import org.yash.tcvm.container.handler.ContainerManager;
import org.yash.tcvm.enums.BlackCoffee;
import org.yash.tcvm.enums.DrinksType;
import org.yash.tcvm.exception.MaterialNotSufficientException;
import org.yash.tcvm.service.interfaces.DrinkService;
import org.yash.tcvm.utils.VendingMachineUtils;

public class BlackCoffeeService implements DrinkService {

	private Logger logger = Logger.getLogger(TeaService.class.getName());
	private ContainerManager containerManager = ContainerManager.getInstance();

	@Override
	public void makeDrink(String drinkType, Integer quantity) {

		if (isMaterialSufficient(quantity)) {

			containerManager.setCoffeeCapacity(containerManager.getCoffeeCapacity()
					- BlackCoffee.COFFEE.getConsumptionAndWasteMaterialTotal() * quantity);
			containerManager.setSugarCapacity(containerManager.getSugarCapacity()
					- BlackCoffee.SUGAR.getConsumptionAndWasteMaterialTotal() * quantity);
			containerManager.setWaterCapacity(containerManager.getWaterCapacity()
					- BlackCoffee.WATER.getConsumptionAndWasteMaterialTotal() * quantity);

			VendingMachineUtils.drinksSales(drinkType, quantity);

			logger.info("Enjoy your drink! You buy " + quantity + " cup of " + drinkType + ", Total cost is "
					+ DrinksType.BLACK_COFFEE.getPrice() * quantity + ".");

		} else {
			throw new MaterialNotSufficientException("Material not sufficient in container.");
		}

	}

	public boolean isMaterialSufficient(Integer quantity) {

		IntPredicate hasCoffee = (q) -> containerManager
				.getCoffeeCapacity() >= BlackCoffee.COFFEE.getConsumptionAndWasteMaterialTotal() * q;
		IntPredicate hasWater = (
				q) -> containerManager.getWaterCapacity() >= BlackCoffee.WATER.getConsumptionAndWasteMaterialTotal() * q;
		IntPredicate hasSugar = (
				q) -> containerManager.getSugarCapacity() >= BlackCoffee.SUGAR.getConsumptionAndWasteMaterialTotal() * q;

		if (hasCoffee.and(hasWater).and(hasSugar).test(quantity))
			return true;
		else
			return false;

	}

}
