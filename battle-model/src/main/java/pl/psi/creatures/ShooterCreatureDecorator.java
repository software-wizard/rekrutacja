package pl.psi.creatures;

import lombok.Getter;
import pl.psi.spells.SpellNames;
import pl.psi.spells.SpellRang;

import static pl.psi.spells.SpellNames.FORGETFULNESS;

@Getter
public class ShooterCreatureDecorator extends AbstractCreature {
    private final Creature decorated;
    private final int maxShots;
    private final double MELEE_PENALTY = 0.5;
    private final ReducedDamageCalculator meleeDamageCalculator = new ReducedDamageCalculator(MELEE_PENALTY);
    private final DefaultDamageCalculator rangeDamageCalculator;
    private int shots;
    private boolean isInMelee = false;
    private double range = Integer.MAX_VALUE;

    public ShooterCreatureDecorator(final Creature aDecorated, final int aShots) {
        super(aDecorated);
        decorated = aDecorated;
        shots = aShots;
        maxShots = aShots;
        rangeDamageCalculator = (DefaultDamageCalculator) decorated.getCalculator();
    }

    @Override
    public void attack(final Creature aDefender) {
        if (isInMelee) {
            decorated.attack(aDefender);
        } else if (canShoot()) {
            attackRange(aDefender);
        }
    }

    private void attackRange(final Creature aDefender) {
        final int damage = getCalculator().calculateDamage(decorated, aDefender);
        decorated.applyDamage(aDefender, damage);
        shots -= 1;
    }

    @Override
    public void setInMelee(final boolean value) {
        if (value) {
            isInMelee = true;
            range = 1.5;
            decorated.setCalculator(meleeDamageCalculator);
        }
        else {
            if(isInMelee) {
                isInMelee = false;
                range = Integer.MAX_VALUE;
                decorated.setCalculator(rangeDamageCalculator);
            }
        }
    }

    @Override
    public String getShotsAmount(){
        return "" + shots;
    }


    @Override
    public boolean isRange(){
        return true;
    }

    @Override
    public double getAttackRange(){
        return range;
    }

    public boolean enoughShots() {
        if (shots > 0) {
            return true;
        } else {
            throw new RuntimeException("No more shots");
        }
    }

    public boolean canShoot(){
        return !isInMelee && enoughShots();
    }

    public void resetShots() {
        shots = maxShots;
    }

    @Override
    public int getShots(){
        if(decorated.getCreatureRunningSpellWithName(FORGETFULNESS).isPresent() && !decorated.getCreatureRunningSpellWithName(FORGETFULNESS).get().getRang().equals(SpellRang.BASIC)){
            return 0;
        }

        return shots;
    }
}
