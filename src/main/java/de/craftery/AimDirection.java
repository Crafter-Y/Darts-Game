package de.craftery;

public enum AimDirection {
    UP(-0.00005d),
    DOWN(0.00005d);

    private final double factor;

    AimDirection(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}
