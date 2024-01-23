package de.raidcomp.model;

import java.util.List;

public record Build(String id, String name, long date, List<Player> players, String raid_id, String instance) {
}
