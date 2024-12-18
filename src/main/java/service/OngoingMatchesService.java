package service;

import entity.Match;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private final Map<String, Match> ongoingMatches;

    public OngoingMatchesService(ConcurrentHashMap<String, Match> ongoingMatches) {
        this.ongoingMatches = ongoingMatches;
    }

    public String add(Match match) {
        String uuid = UUID.randomUUID().toString();
        ongoingMatches.put(uuid, match);

        return uuid;
    }

    public Optional<Match> get(String uuid) {
        return ongoingMatches.containsKey(uuid) ? Optional.of(ongoingMatches.get(uuid)) : Optional.empty();
    }

    public void remove(String uuid) {
        ongoingMatches.remove(uuid);
    }
}
