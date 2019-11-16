package dao.impl.mysql;

import dao.GameDAO;
import gamestore.exceptions.NoMatchingId;
import gamestore.models.Game;

import java.util.Collection;
import java.util.UUID;

public class GameDAOMYSQL implements GameDAO {
    public void createGame(Game game) {
        
    }

    public Collection<Game> readAllGames() {
        return null;
    }

    public Game readGame(UUID id) throws NoMatchingId {
        return null;
    }

    public void updateGame(Game game) throws NoMatchingId {

    }

    public void deleteGame(Game game) throws NoMatchingId {

    }
}
