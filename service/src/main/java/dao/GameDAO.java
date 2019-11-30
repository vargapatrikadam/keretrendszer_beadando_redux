package dao;

import gamestore.exceptions.NoMatchingId;
import gamestore.models.Game;

import java.util.Collection;
import java.util.UUID;

public interface GameDAO {
    void createGame(Game game);
    Collection<Game> readAllGames();
    Game readGame(UUID id) throws NoMatchingId;
    void updateGame(UUID id, Game game) throws NoMatchingId;
    void deleteGame(Game game) throws NoMatchingId;
}
