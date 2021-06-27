package tfar.advancementpoints;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;

public class AdvancementPoints implements ModInitializer,  ServerLifecycleEvents.ServerStarted{

	public static final String MODID = "advancementpoints";
	public static AdvancementPoints instance;
	public AdvancementPoints() {
		instance = this;
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ServerLifecycleEvents.SERVER_STARTED.register(this);
	}

	@Override
	public void onServerStarted(MinecraftServer server) {
		setupScoreboard(server);
	}

	public static void setupScoreboard(MinecraftServer server) {
		ServerScoreboard scoreboard = server.getScoreboard();
		if (scoreboard.getObjective(Events.OBJECTIVE) == null) {
			scoreboard.addObjective(Events.OBJECTIVE, ObjectiveCriteria.DUMMY, new TextComponent("AdvancementPoints"), ObjectiveCriteria.RenderType.INTEGER);
		}
		int slot = Scoreboard.getDisplaySlotByName("sidebar");
		scoreboard.setDisplayObjective(slot,scoreboard.getObjective(Events.OBJECTIVE));
	}
}
