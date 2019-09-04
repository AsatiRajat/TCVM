package org.yash.tcvm;

import java.util.InputMismatchException;
import java.util.logging.Logger;

import org.yash.tcvm.container.handler.ContainerManager;
import org.yash.tcvm.enums.DrinksType;
import org.yash.tcvm.exception.InvalidChoiceException;
import org.yash.tcvm.factory.DrinkServiceFactory;
import org.yash.tcvm.service.interfaces.DrinkService;
import org.yash.tcvm.utils.Constants;
import org.yash.tcvm.utils.CustomScanner;

public class VendingMachineOptions {

	// private static final
	Logger logger = Logger.getLogger(VendingMachineOptions.class.getName());

	private ContainerManager containerManager = ContainerManager.getInstance();
	private DrinkServiceFactory drinkServiceFactory = new DrinkServiceFactory();
	private CustomScanner scanner = new CustomScanner();

	public void vendingMachineOptions(Boolean isOptionSelected) {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n\u001B[30m");
		Boolean isContinue = true;
		Integer selectedOptionChoice = 0;
		Integer refillContainerChoice = 0;
		Integer quantity = 0;
		Integer refillCount = 0;
		DrinkService drinkService = null;

		try {
			do {

				logger.info(Constants.GREETING_MSG);
				selectedOptionChoice = scanner.nextInt();
				drinkService = drinkServiceFactory.getDrinkServiceInstance(selectedOptionChoice);

				switch (selectedOptionChoice) {
				case 1:
					logger.info("How many cup of Tea you want?");
					quantity = scanner.nextInt();
					drinkService.makeDrink(DrinksType.TEA.name(), quantity);
					logger.info("Your Tea is prepared");
					break;
				case 2:
					logger.info("How many cup of BlackTea you want?");
					quantity = scanner.nextInt();
					drinkService.makeDrink(DrinksType.BLACK_TEA.name(), quantity);
					logger.info("Your Black Tea is prepared");
					break;
				case 3:
					logger.info("How many cup of Coffee you want?");
					quantity = scanner.nextInt();
					drinkService.makeDrink(DrinksType.COFFEE.name(), quantity);
					logger.info("Your Coffee is prepared");
					break;
				case 4:
					logger.info("How many cup of BlackCoffee you want?");
					quantity = scanner.nextInt();
					drinkService.makeDrink(DrinksType.BLACK_COFFEE.name(), quantity);
					logger.info("Your Black Coffee is prepared");
					break;
				case 5:
					containerManager.resetContainer();
					break;
				case 6:
					containerManager.checkTotalSale();
					break;
				case 7:
					containerManager.containerStatus();
					break;
				case 8:
					do {
						logger.info(
								"\nWhich container do you want to refill." + "\n1. Refill Tea " + "\n2. Refill Coffee "
										+ "\n3. Refill Sugar " + "\n4. Refill Water " + "\n5. Refill Milk ");
						refillContainerChoice = scanner.nextInt();
						refillCount = containerManager.refillContainer(refillContainerChoice);
						logger.info("Container Refill Count is : " + refillCount);
						logger.info("Do you want to continue if yes enter 1 else 0");
						isContinue = scanner.nextInt() == 1 ? true : false;
					} while (isContinue);
					break;
				case 9:
					isOptionSelected = false;
					logger.info("TVCM exiting... Thanks for using TVCM.");
					/** System.exit(0); */
					break;
				default:
					logger.info("Invalid choice, Your selection should be 0 to 9.");
				}

			} while (isOptionSelected);

		} catch (InputMismatchException e) {
			logger.info("Invalid choice, Please Enter the correct Choice here.");
			throw new InvalidChoiceException("Invalid choice, Please Enter the correct Choice here.");
		}

	}

}
