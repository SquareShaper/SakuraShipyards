package sakura_shipyards.data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.campaign.ids.Tags;

public class LimitedCommandBridge extends BaseHullMod {

    @Override
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getFighterWingRange().modifyMult(id, 0); // Make fighters stick to ship
    }

    @Override
    public void applyEffectsToFighterSpawnedByShip(ShipAPI fighter, ShipAPI ship, String id) {
        if (fighter.getWing() != null && fighter.getWing().getSpec() != null) {
            if (fighter.getWing().getSpec().isRegularFighter() || fighter.getWing().getSpec().isAssault() ||
                    fighter.getWing().getSpec().isBomber() || fighter.getWing().getSpec().isInterceptor()) {
                fighter.addTag(Tags.WING_STAY_IN_FRONT_OF_SHIP);
            }
        }
    }

    @Override
    public boolean isApplicableToShip(ShipAPI ship) {
        int bays = (int) ship.getMutableStats().getNumFighterBays().getModifiedValue();
        return ship != null && bays > 0;
    }

    public String getUnapplicableReason(ShipAPI ship) {
        return "Ship does not have fighter bays";
    }
}
