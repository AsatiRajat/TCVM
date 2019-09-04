package org.yash.tcvm.container.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.yash.tcvm.enums.DrinksType;

public class ContainerManager {

	private int teaCapacity;
	private int coffeeCapacity;
	private int sugarCapacity;
	private int waterCapacity;
	private int milkCapacity;
	private int teaWastage;
	private int milkWastage;
	private int sugarWastage;
	private int waterWastage;
	private int coffeeWastage;
	private Integer refillCounter = 0;
	private static ContainerManager containerManager;
	private ConcurrentMap<String, Integer> drinkSaleReportMap = new ConcurrentHashMap<>();
	// private Map<String, Integer> wastageMaterialMap = new HashMap<>();

	Logger logger = Logger.getLogger(ContainerManager.class.getName());

	private ContainerManager() {
		intializedContainer();
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n\u001B[30m");
	}

	public static ContainerManager getInstance() {
		if (containerManager == null) {
			containerManager = new ContainerManager();
			return containerManager;
		}
		return containerManager;
	}

	public int getTeaCapacity() {
		return teaCapacity;
	}

	public void setTeaCapacity(int teaCapacity) {
		this.teaCapacity = teaCapacity;
	}

	public int getCoffeeCapacity() {
		return coffeeCapacity;
	}

	public void setCoffeeCapacity(int coffeeCapacity) {
		this.coffeeCapacity = coffeeCapacity;
	}

	public int getSugarCapacity() {
		return sugarCapacity;
	}

	public void setSugarCapacity(int sugarCapacity) {
		this.sugarCapacity = sugarCapacity;
	}

	public int getWaterCapacity() {
		return waterCapacity;
	}

	public void setWaterCapacity(int waterCapacity) {
		this.waterCapacity = waterCapacity;
	}

	public int getMilkCapacity() {
		return milkCapacity;
	}

	public void setMilkCapacity(int milkCapacity) {
		this.milkCapacity = milkCapacity;
	}

	public int getTeaWastage() {
		return teaWastage;
	}

	public void setTeaWastage(int teaWastage) {
		this.teaWastage = teaWastage;
	}

	public int getMilkWastage() {
		return milkWastage;
	}

	public void setMilkWastage(int milkWastage) {
		this.milkWastage = milkWastage;
	}

	public int getSugarWastage() {
		return sugarWastage;
	}

	public void setSugarWastage(int sugarWastage) {
		this.sugarWastage = sugarWastage;
	}

	public int getWaterWastage() {
		return waterWastage;
	}

	public void setWaterWastage(int waterWastage) {
		this.waterWastage = waterWastage;
	}

	public int getCoffeeWastage() {
		return coffeeWastage;
	}

	public void setCoffeeWastage(int coffeeWastage) {
		this.coffeeWastage = coffeeWastage;
	}

	public Map<String, Integer> getDrinkSaleReportMap() {
		return drinkSaleReportMap;
	}

	public void intializedContainer() {
		teaCapacity = 2000;
		coffeeCapacity = 2000;
		sugarCapacity = 8000;
		waterCapacity = 15000;
		milkCapacity = 10000;
		teaWastage = 0;
		milkWastage = 0;
		sugarWastage = 0;
		waterWastage = 0;
		coffeeWastage = 0;
	}

	public void resetContainer() {
		intializedContainer();
		logger.info("\n*************** Containet has been been Reset. ***************");
		logger.info("			Tea	Coffee	Milk	Sugar	Water");
		logger.info("Used Material : \t" + getTeaCapacity() + "    " + getCoffeeCapacity() + "\t" + getMilkCapacity()
				+ "\t" + getSugarCapacity() + "\t" + getWaterCapacity() + "\t");
		logger.info("Wastage Material : \t" + getTeaWastage() + "\t" + getCoffeeWastage() + "\t" + getMilkWastage()
				+ "\t" + getSugarWastage() + "\t" + getWaterWastage() + "\t");
	}

	public void checkTotalSale() {

		int drinksPrice = 0;
		int totalDrinkSalePrice = 0;

		logger.info("\n*************** Total Tea-Coffee Sale Report Drink Wise ***************");
		for (Map.Entry<String, Integer> entry : drinkSaleReportMap.entrySet()) {
			if ("TEA".equals(entry.getKey())) {
				drinksPrice = DrinksType.TEA.getPrice();
			}
			if ("BLACK_TEA".equals(entry.getKey())) {
				drinksPrice = DrinksType.BLACK_TEA.getPrice();
			}
			if ("COFFEE".equals(entry.getKey())) {
				drinksPrice = DrinksType.COFFEE.getPrice();
			}
			if ("BLACK_COFFEE".equals(entry.getKey())) {
				drinksPrice = DrinksType.BLACK_COFFEE.getPrice();
			}

			totalDrinkSalePrice += drinksPrice * entry.getValue();

			logger.info("Sale of " + entry.getKey() + " with Quantity : " + entry.getValue() + " and Price : "
					+ drinksPrice * entry.getValue());

		}

		Integer totalDrinkQuantity = drinkSaleReportMap.entrySet().stream()
				.collect(Collectors.summingInt(saleMap -> saleMap.getValue()));

		logger.info("\n*************** Total Tea-Coffee Sale (Cup and Cost) ***************");
		logger.info("Total Drink Quantity is " + totalDrinkQuantity + " Total Cost Is " + totalDrinkSalePrice);

		calculateTotalWastedMaterial(drinkSaleReportMap);

		logger.info("\n*************** Total Waste Material ***************");
		logger.info("Wasted material for drinks." + "\nTotal Tea Wastage is " + getTeaWastage() + " gm"
				+ "\nTotal Coffee Wastage is " + getCoffeeWastage() + " gm" + "\nTotal Sugar Wastage is "
				+ getSugarWastage() + " gm" + "\nTotal Water Wastage is " + getWaterWastage() + " ml"
				+ "\nTotal Milk Wastage is " + getMilkWastage() + " ml");

	}

	public void containerStatus() {
		logger.info("\n*************** Available Material in Container ***************");
		logger.info("Tea Container Status :" + getTeaCapacity() + "\nCoffee Container Status :" + getCoffeeCapacity()
				+ "\nMilk Container Status :" + getMilkCapacity() + "\nSugar Container Status :" + getSugarCapacity()
				+ "\nWater Container Status :" + getWaterCapacity());
	}

	public Integer refillContainer(Integer choice) {
		Boolean isRefilled = false;
		switch (choice) {
		case 1:
			refillCounter += 1;
			isRefilled = true;
			teaCapacity = 2000;
			logger.info("Tea capacity has been Refilled.");
			break;
		case 2:
			refillCounter += 1;
			isRefilled = true;
			coffeeCapacity = 2000;
			logger.info("Coffee capacity has been Refilled.");
			break;
		case 3:
			refillCounter += 1;
			isRefilled = true;
			sugarCapacity = 8000;
			logger.info("Sugar capacity has been Refilled.");
			break;
		case 4:
			refillCounter += 1;
			isRefilled = true;
			waterCapacity = 15000;
			logger.info("Water capacity has been Refilled.");
			break;
		case 5:
			refillCounter += 1;
			isRefilled = true;
			milkCapacity = 10000;
			logger.info("Milk capacity has been Refilled.");
			break;
		default:
			logger.info("Invalid choice, Your selection should be 0 to 9.");
		}

		return isRefilled ? refillCounter : 0;
	}

	public void calculateTotalWastedMaterial(Map<String, Integer> saleReportMap) {

		if (saleReportMap.containsKey("TEA")) {
			setTeaWastage(teaWastage += saleReportMap.get("TEA") * 1);
			setMilkWastage(milkWastage += saleReportMap.get("TEA") * 4);
			setSugarWastage(sugarWastage += saleReportMap.get("TEA") * 2);
			setWaterWastage(waterWastage += saleReportMap.get("TEA") * 5);
		}
		if (saleReportMap.containsKey("COFFEE")) {
			setCoffeeWastage(coffeeWastage += saleReportMap.get("COFFEE") * 1);
			setMilkWastage(milkWastage += saleReportMap.get("COFFEE") * 8);
			setSugarWastage(sugarWastage += saleReportMap.get("COFFEE") * 2);
			setWaterWastage(waterWastage += saleReportMap.get("COFFEE") * 3);
		}
		if (saleReportMap.containsKey("BLACK_TEA")) {
			setSugarWastage(sugarWastage += saleReportMap.get("BLACK_TEA") * 2);
			setWaterWastage(waterWastage += saleReportMap.get("BLACK_TEA") * 12);
		}
		if (saleReportMap.containsKey("BLACK_COFFEE")) {
			setSugarWastage(sugarWastage += saleReportMap.get("BLACK_COFFEE") * 2);
			setWaterWastage(waterWastage += saleReportMap.get("BLACK_COFFEE") * 12);
		}

	}

}
