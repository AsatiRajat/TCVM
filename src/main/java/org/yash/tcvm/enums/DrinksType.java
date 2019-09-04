package org.yash.tcvm.enums;

public enum DrinksType {
	TEA("TEA", 10), COFFEE("COFFEE", 15), BLACK_COFFEE("BLACK_COFFEE", 10), BLACK_TEA("BLACK_TEA", 5);

	private final Integer price;

	DrinksType(String name, Integer price) {
		this.price = price;
	}

	public Integer getPrice() {
		return price;
	}
}
