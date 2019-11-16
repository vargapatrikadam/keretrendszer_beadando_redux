package services;

import gamestore.exceptions.DateIsTooLate;
import gamestore.exceptions.NoMatchingId;
import gamestore.models.Game;

import java.util.Collection;
import java.util.UUID;

public interface GameStoreService {
    public Collection<Game> getAllGames();
    public Game getGame(UUID id) throws NoMatchingId;
    public void addGame(Game game) throws DateIsTooLate;
    public void updateGame(Game game) throws NoMatchingId, DateIsTooLate;
    public void deleteGame(Game game) throws NoMatchingId;
    public void deleteGame(UUID id) throws NoMatchingId;
}
