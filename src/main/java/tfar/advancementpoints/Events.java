package tfar.advancementpoints;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.network.Connection;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;

import java.util.EnumMap;
import java.util.Map;

public class Events {

    public static final String OBJECTIVE = "AdvScore";
    private static final Map<FrameType,Integer> pointMap = new EnumMap<>(FrameType.class);

    static {
        pointMap.put(FrameType.TASK,5);
        pointMap.put(FrameType.GOAL,10);
        pointMap.put(FrameType.CHALLENGE,15);
    }

    public static void onAwardAdvancement(Advancement advancement, String criterion, ServerPlayer player) {
        DisplayInfo displayInfo = advancement.getDisplay();
        if (displayInfo != null) {
            int points = pointMap.get(displayInfo.getFrame());
            addPoints(player,points);
        }
    }

    public static void onPlayerLogin(Connection connection, ServerPlayer player) {
        ServerScoreboard scoreboard = player.getServer().getScoreboard();
        Objective objective = scoreboard.getObjective(OBJECTIVE);
        Score score = scoreboard.getOrCreatePlayerScore(player.getDisplayName().getString(), objective);
    }

    public static void addPoints(ServerPlayer player, int points) {
        Scoreboard scoreboard = player.getServer().getScoreboard();
        Objective objective = scoreboard.getObjective(OBJECTIVE);

        Score score = scoreboard.getOrCreatePlayerScore(player.getDisplayName().getString(), objective);
        score.setScore(score.getScore() + points);

    }
}
