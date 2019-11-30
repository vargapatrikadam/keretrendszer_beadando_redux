package services;

import gamestore.exceptions.DateIsTooLate;
import gamestore.exceptions.NoMatchingId;
import gamestore.models.Game;

import java.util.Collection;
import java.util.UUID;

public interface GameStoreService {
    Collection<Game> getAllGames();
    Game getGame(UUID id) throws NoMatchingId;
    void addGame(Game game) throws DateIsTooLate;
    void updateGame(UUID id, Game game) throws NoMatchingId, DateIsTooLate;
    void deleteGame(Game game) throws NoMatchingId;
    void deleteGame(UUID id) throws NoMatchingId;
}
