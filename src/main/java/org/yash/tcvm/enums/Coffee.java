package org.yash.tcvm.enums;

public enum Coffee {
	COFFEE(4, 1), MILK(80, 8), SUGAR(15, 2), WATER(20, 3);

	private final Integer consumption;
	private final Integer waste;

	Coffee(Integer consumption, Integer waste) {
		this.consumption = consumption;
		this.waste = waste;
	}

	public Integer getConsumptionAndWasteMaterialTotal() {
		return consumption + waste;
	}
}
