package org.yash.tcvm.enums;

public enum BlackTea {
	TEA(3, 0), SUGAR(15, 2), WATER(100, 12);

	private final Integer consumption;
	private final Integer waste;

	BlackTea(Integer consumption, Integer waste) {
		this.consumption = consumption;
		this.waste = waste;
	}

	public Integer getConsumptionAndWasteMaterialTotal() {
		return consumption + waste;
	}
}
