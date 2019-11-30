package services.impl;

import dao.GameDAO;
import gamestore.exceptions.DateIsTooLate;
import gamestore.exceptions.NoMatchingId;
import gamestore.models.Game;
import services.GameStoreService;

import java.util.Collection;
import java.util.UUID;

public class GameStoreServiceImplementation implements GameStoreService {
    private GameDAO dao;

    public GameStoreServiceImplementation(GameDAO dao) {
        this.dao = dao;
    }

    public Collection<Game> getAllGames() {
        return this.dao.readAllGames();
    }

    public Game getGame(UUID id) throws NoMatchingId {
        return this.dao.readGame(id);
    }

    public void addGame(Game game) throws DateIsTooLate {
        this.dao.createGame(game);
    }

    public void updateGame(UUID id, Game game) throws NoMatchingId, DateIsTooLate {
        this.dao.updateGame(id, game);
    }

    public void deleteGame(Game game) throws NoMatchingId {
        this.dao.deleteGame(game);
    }

    public void deleteGame(UUID id) throws NoMatchingId {
        Game result = this.dao.readGame(id);
        deleteGame(result);
    }
}
