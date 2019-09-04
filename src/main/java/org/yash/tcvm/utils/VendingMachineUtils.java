package org.yash.tcvm.utils;

import org.yash.tcvm.container.handler.ContainerManager;

public class VendingMachineUtils {

	private static ContainerManager containerManager = ContainerManager.getInstance();

	public static void drinksSales(String drinkType, Integer quantity) {

		if (containerManager.getDrinkSaleReportMap().containsKey(drinkType)) {
			containerManager.getDrinkSaleReportMap().put(drinkType,
					containerManager.getDrinkSaleReportMap().get(drinkType) + quantity);
		} else {
			containerManager.getDrinkSaleReportMap().put(drinkType, quantity);
		}
	}

	// public static void calculateWastageMaterial(String drinkType, Integer
	// quantity) {
	//
	// if (vendingMachine.getDrinkSaleReportMap().containsKey(drinkType))
	// vendingMachine.getDrinkSaleReportMap().put(drinkType,
	// vendingMachine.getDrinkSaleReportMap().get(drinkType) + quantity);
	// else
	// vendingMachine.getDrinkSaleReportMap().put(drinkType, quantity);
	//
	// }

}
