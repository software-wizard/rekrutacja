package pl.psi.creatures;


public class ChargingDecorator extends AbstractCreature{
    private final Creature decorated;
    private double CHARGING_BONUS = 1.05;



    public ChargingDecorator(Creature aDecorated) {
        super(aDecorated);
        decorated = aDecorated;
    }

    @Override
    public void attack(final Creature aDefender) {

        ChargingDamageCalculator chargingDamageCalculator = new ChargingDamageCalculator(CHARGING_BONUS,getTraversedFieldsNumber());
        decorated.setCalculator(chargingDamageCalculator);
        decorated.attack(aDefender);
    }


}
