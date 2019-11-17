package gamestore.controllers;

import gamestore.exceptions.DateIsTooLate;
import gamestore.exceptions.NoMatchingId;
import gamestore.models.Category;
import gamestore.models.Game;
import gamestore.models.Platform;
import gamestore.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.GameStoreService;

import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class GameStoreController {
    GameStoreService service;

    public GameStoreController(@Autowired GameStoreService service) {
        this.service = service;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String showGamesCount(){
        return String.valueOf(service.getAllGames().size());
    }

    @RequestMapping(value = "/getgames", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Game> getAllGames() throws DateIsTooLate {
        return service.getAllGames();
    }

    @RequestMapping(value = "/addnewgame", method = RequestMethod.POST)
    @ResponseBody
    public Game addNewGame(@RequestBody Game game) throws DateIsTooLate, NoMatchingId {
        service.addGame(game);
        return service.getGame(game.getId());
    }
}
