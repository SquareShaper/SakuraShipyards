package sakura_shipyards.data.shipsystems;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import org.lazywizard.lazylib.combat.AIUtils;

import java.util.List;

public class NovaFieldSystem extends BaseShipSystemScript {

    protected float SYSTEM_RANGE = 500;

    @Override
    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        if (state != State.ACTIVE) {
            return;
        }

        ShipAPI ship;
        //boolean player = false;
        if (stats.getEntity() instanceof ShipAPI) {
            ship = (ShipAPI) stats.getEntity();
        } else {
            return;
        }

        List<ShipAPI> victims = AIUtils.getNearbyEnemies(ship, SYSTEM_RANGE); // get nearby enemies
        victims.addAll(AIUtils.getNearbyAllies(ship, SYSTEM_RANGE)); // also add in nearby allies

        ship.getVelocity().scale(0); // stop own velocity
        if (!victims.isEmpty()) {
            for (ShipAPI victim : victims) {
                //victim.getEngineController().getShipEngines().forEach(e -> e.disable()); // disable victim engines
                victim.getVelocity().scale(0); // stop victim velocity
            }
        }
    }
}
