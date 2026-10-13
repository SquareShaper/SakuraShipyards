package sakura_shipyards.data.shipsystems;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.util.IntervalUtil;
import org.lwjgl.util.vector.Vector2f;

public class NovaFieldAI implements ShipSystemAIScript {

    protected ShipAPI ship;
    protected CombatEngineAPI engine;
    protected ShipwideAIFlags flags;
    protected ShipSystemAPI system;
    protected NovaFieldSystem script;

    protected IntervalUtil tracker = new IntervalUtil(0.5f, 1f);

    @Override
    public void init(ShipAPI ship, ShipSystemAPI system, ShipwideAIFlags flags, CombatEngineAPI engine) {
        this.ship = ship;
        this.flags = flags;
        this.engine = engine;
        this.system = system;

        script = (NovaFieldSystem)system.getScript();
    }

    @Override
    public void advance(float amount, Vector2f missileDangerDir, Vector2f collisionDangerDir, ShipAPI target) {
        tracker.advance(amount);

        if (tracker.intervalElapsed()) {
            if (system.getCooldownRemaining() > 0) return;
            if (system.isOutOfAmmo()) return;
            if (system.isActive()) return;
            if (ship.getFluxTracker().isOverloadedOrVenting()) return;

            // do something
        }
    }
}
