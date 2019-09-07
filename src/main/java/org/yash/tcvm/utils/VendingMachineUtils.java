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

}
