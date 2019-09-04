package org.yash.tcvm.enums;

public enum Tea {
	TEA(5, 1), MILK(40, 4), SUGAR(15, 2), WATER(60, 5);

	private final Integer consumption;
	private final Integer waste;

	Tea(Integer consumption, Integer waste) {
		this.consumption = consumption;
		this.waste = waste;
	}

	public Integer getConsumptionAndWasteMaterialTotal() {
		return consumption + waste;
	}
}
