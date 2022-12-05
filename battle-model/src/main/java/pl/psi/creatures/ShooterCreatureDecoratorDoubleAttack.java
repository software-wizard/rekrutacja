package pl.psi.creatures;

/* Shooters who shoot twice do not get the second attack if engaged in a melee fight */

public class ShooterCreatureDecoratorDoubleAttack extends ShooterCreatureDecorator{
    private final Creature decorated;
    private final int maxShots;
    private final double MELEE_PENALTY = 0.5;
    private final ReducedDamageCalculator meleeDamageCalculator = new ReducedDamageCalculator(MELEE_PENALTY);
    private final DefaultDamageCalculator rangeDamageCalculator;
    private int shots;
    private boolean isInMelee = false;
    private double range = Integer.MAX_VALUE;

    public ShooterCreatureDecoratorDoubleAttack(final Creature aDecorated, final int aShot) {
        super(aDecorated,aShot);
        decorated = aDecorated;
        maxShots = aShot;
        rangeDamageCalculator = (DefaultDamageCalculator) decorated.getCalculator();
    }
    @Override
    public void attack(final Creature aDefender1) {
        if (isInMelee()) {
            decorated.attack(aDefender1);
        } else if (canShoot()) {
            attackRange(aDefender1);
            attackRange(aDefender1);
        }
    }

}
