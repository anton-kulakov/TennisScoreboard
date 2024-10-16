package service;

import entity.Match;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private final Map<String, Match> ongoingMatchesMap;
    public OngoingMatchesService() {
        ongoingMatchesMap = new ConcurrentHashMap<>();
    }
    public String addMatch(Match match) {
        UUID uuid = UUID.randomUUID();
        while (ongoingMatchesMap.containsKey(uuid.toString())) {
            uuid = UUID.randomUUID();
        }

        ongoingMatchesMap.put(uuid.toString(), match);
        return uuid.toString();
    }
}
