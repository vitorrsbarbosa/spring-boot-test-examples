package com.barbosa.currency.unit;

public class CurrencyUnit {
    private final String unit;

    public CurrencyUnit(String unit) {
        this.unit = unit;
    }

    public static CurrencyUnit of(String unit) {
        return new CurrencyUnit(unit);
    }

    public String getUnit() {
        return unit;
    }
}
