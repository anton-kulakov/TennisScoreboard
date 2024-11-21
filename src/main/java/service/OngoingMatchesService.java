package service;

import entity.Match;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private final Map<String, Match> ongoingMatches;
    public OngoingMatchesService() {
        ongoingMatches = new ConcurrentHashMap<>();
    }
    public String addMatch(Match match) {
        UUID uuid = UUID.randomUUID();

        while (ongoingMatches.containsKey(uuid.toString())) {
            uuid = UUID.randomUUID();
        }
        ongoingMatches.put(uuid.toString(), match);

        return uuid.toString();
    }

    public Optional<Match> getMatch(String uuid) {
        return ongoingMatches.containsKey(uuid) ? Optional.of(ongoingMatches.get(uuid)) : Optional.empty();
    }
}
