package org.yash.tcvm.service;

import java.util.function.IntPredicate;
import java.util.logging.Logger;

import org.yash.tcvm.container.handler.ContainerManager;
import org.yash.tcvm.enums.Coffee;
import org.yash.tcvm.enums.DrinksType;
import org.yash.tcvm.exception.MaterialNotSufficientException;
import org.yash.tcvm.service.interfaces.DrinkService;
import org.yash.tcvm.utils.VendingMachineUtils;

public class CoffeeService implements DrinkService {

	private Logger logger = Logger.getLogger(TeaService.class.getName());
	private ContainerManager containerManager = ContainerManager.getInstance();

	@Override
	public void makeDrink(String drinkType, Integer quantity) {

		if (isMaterialSufficient(quantity)) {

			containerManager.setCoffeeCapacity(containerManager.getCoffeeCapacity()
					- Coffee.COFFEE.getConsumptionAndWasteMaterialTotal() * quantity);
			containerManager.setMilkCapacity(
					containerManager.getMilkCapacity() - Coffee.MILK.getConsumptionAndWasteMaterialTotal() * quantity);
			containerManager.setSugarCapacity(
					containerManager.getSugarCapacity() - Coffee.SUGAR.getConsumptionAndWasteMaterialTotal() * quantity);
			containerManager.setWaterCapacity(
					containerManager.getWaterCapacity() - Coffee.WATER.getConsumptionAndWasteMaterialTotal() * quantity);

			VendingMachineUtils.drinksSales(drinkType, quantity);

			logger.info("Enjoy your drink! You buy " + quantity + " cup of " + drinkType + ", Total cost is "
					+ DrinksType.COFFEE.getPrice() * quantity + ".");

		} else {
			throw new MaterialNotSufficientException("Material not sufficient in container.");
		}

	}

	public boolean isMaterialSufficient(Integer quantity) {

		IntPredicate hasCoffee = (
				q) -> containerManager.getCoffeeCapacity() >= Coffee.COFFEE.getConsumptionAndWasteMaterialTotal() * q;
		IntPredicate hasWater = (
				q) -> containerManager.getWaterCapacity() >= Coffee.WATER.getConsumptionAndWasteMaterialTotal() * q;
		IntPredicate hasMilk = (
				q) -> containerManager.getMilkCapacity() >= Coffee.MILK.getConsumptionAndWasteMaterialTotal() * q;
		IntPredicate hasSugar = (
				q) -> containerManager.getSugarCapacity() >= Coffee.SUGAR.getConsumptionAndWasteMaterialTotal() * q;

		if (hasCoffee.and(hasWater).and(hasMilk).and(hasSugar).test(quantity))
			return true;
		else
			return false;

	}

}
