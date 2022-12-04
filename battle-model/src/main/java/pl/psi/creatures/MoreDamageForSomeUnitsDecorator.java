package pl.psi.creatures;

import java.util.Map;

/* Map<String, Integer> units contains information about name and damage for some units e.g. <"Devil",1.5> means
* 150% damage for Devil units */

public class MoreDamageForSomeUnitsDecorator extends AbstractCreature {
    private final Creature decorated;
    private final Map<String, Double> units;
    private final DefaultDamageCalculator defaultDamageCalculator;
    public MoreDamageForSomeUnitsDecorator(Creature aDecorated, Map<String, Double> aUnits) {
        super(aDecorated);
        decorated = aDecorated;
        units=aUnits;
        defaultDamageCalculator = (DefaultDamageCalculator) decorated.getCalculator();
    }
    @Override
    public void attack(final Creature aDefender) {
        if (units.keySet().contains(aDefender.getName())) {
            ReducedDamageCalculator reducedDamageCalculator = new ReducedDamageCalculator(units.get(aDefender.getName()));
            decorated.setCalculator(reducedDamageCalculator);
            decorated.attack(aDefender);

        } else {
            decorated.setCalculator(defaultDamageCalculator);
            decorated.attack(aDefender);
        }
    }



}
