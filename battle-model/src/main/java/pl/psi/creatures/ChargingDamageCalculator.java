package pl.psi.creatures;

import java.util.Random;

public class ChargingDamageCalculator extends AbstractCalculateDamageStrategy {
    private final double increaseBy;
    private int traversedFieldsNumber;

    public ChargingDamageCalculator(double aIncreaseBy, int aTraversedFieldsNumber) {
        super(new Random());
        increaseBy = aIncreaseBy;
        traversedFieldsNumber = aTraversedFieldsNumber;
    }

    @Override
    public int calculateDamage(final Creature aAttacker, final Creature aDefender) {
        AbstractCalculateDamageStrategy defaultCalculator = new DefaultDamageCalculator(new Random());
        return (int) (defaultCalculator.calculateDamage(aAttacker, aDefender) * calculateBonus());
    }

    private double calculateBonus(){
        double bonus = 1;
    for (int i = 0; i<traversedFieldsNumber;i++){
        bonus = bonus * increaseBy;
    }
    return bonus;
    }
}
