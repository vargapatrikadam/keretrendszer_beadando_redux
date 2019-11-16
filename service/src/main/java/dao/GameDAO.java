package dao;

import gamestore.exceptions.NoMatchingId;
import gamestore.models.Game;

import java.util.Collection;
import java.util.UUID;

public interface GameDAO {
    public void createGame(Game game);
    public Collection<Game> readAllGames();
    public Game readGame(UUID id) throws NoMatchingId;
    public void updateGame(Game game) throws NoMatchingId;
    public void deleteGame(Game game) throws NoMatchingId;
}
