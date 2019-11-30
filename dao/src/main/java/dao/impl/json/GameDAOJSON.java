package dao.impl.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.GameDAO;
import gamestore.exceptions.NoMatchingId;
import gamestore.models.Game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class GameDAOJSON implements GameDAO {
    File jsonFile;
    ObjectMapper mapper;

    public GameDAOJSON(File jsonFile) {
        this.jsonFile = jsonFile;
        if (!jsonFile.exists()){
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFile);
                writer.write("[]");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public GameDAOJSON(String jsonFilePath){
        this.jsonFile = new File(jsonFilePath);
        if(!jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFilePath);
                writer.write("[]");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public void createGame(Game game) {
        Collection<Game> games = readAllGames();
        games.add(game);
        try{
            mapper.writeValue(jsonFile, games);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Collection<Game> readAllGames() {
        Collection<Game> games = new ArrayList<Game>();
        try{
            games = mapper.readValue(jsonFile, new TypeReference<ArrayList<Game>>(){});
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return games;
    }

    public Game readGame(UUID id) throws NoMatchingId {
        Collection<Game> games = readAllGames();
        for (Game g: games){
            if(g.getId().toString().equalsIgnoreCase(id.toString())){
                return g;
            }
        }
        throw new NoMatchingId(id.toString());
    }

    public void deleteGame(Game game) throws NoMatchingId {
        Collection<Game> szereplok = readAllGames();
        Collection<Game> result = new ArrayList<Game>();
        try {
            Game deleteThis = readGame(game.getId());

            for(Game sz : szereplok){
                if(!(sz.getId().equals(deleteThis.getId()))){
                    result.add(sz);
                }
            }

            mapper.writeValue(jsonFile, result);
        } catch (NoMatchingId noMatchingId) {
            throw noMatchingId;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateGame(UUID id, Game game) throws NoMatchingId {
        Game deleteThis = readGame(id);
        deleteGame(deleteThis);
        createGame(game);
    }
}
