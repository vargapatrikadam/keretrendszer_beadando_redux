package gamestore.controllers;

import gamestore.controllers.exceptions.FilterIsMissingException;
import gamestore.exceptions.DateIsTooLate;
import gamestore.exceptions.NoMatchingId;
import gamestore.models.Category;
import gamestore.models.Game;
import gamestore.models.Platform;
import gamestore.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import services.GameStoreService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

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

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Game> getAllGames() throws DateIsTooLate {
        return service.getAllGames();
    }

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    @ResponseBody
    public Game addNewGame(@RequestBody Game game) throws DateIsTooLate, NoMatchingId {
        service.addGame(game);
        return service.getGame(game.getId());
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Game updateGame(@PathVariable UUID id, @RequestBody Game game) throws DateIsTooLate, NoMatchingId {
        game.setId(id);
        service.updateGame(id, game);
        return game;
    }


    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteGame(@PathVariable UUID id) throws NoMatchingId {
        service.deleteGame(id);
        return "Delete successful!";
    }

    @RequestMapping(value = "/games/", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Game> getGamesByFilter(
            @RequestParam(required = false) String rating,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String platform) throws FilterIsMissingException {
        if (rating == null && category == null && platform == null)
            throw new FilterIsMissingException("You have to specify atleast one filter!");

        Collection<Game> games = service.getAllGames();
        Collection<Game> result = new ArrayList<>();

        for (Game g: games) {
            if(rating != null && category != null && platform != null){
                if(g.getRating().equals(Rating.valueOf(rating)) &&
                   g.getCategories().contains(Category.valueOf(category)) &&
                   g.getPlatforms().contains(Platform.valueOf(platform))){
                    result.add(g);
                    continue;
                }
                continue;
            }

            if(rating != null && category != null){
                if(g.getRating().equals(Rating.valueOf(rating)) &&
                   g.getCategories().contains(Category.valueOf(category))){
                    result.add(g);
                    continue;
                }
                continue;
            }

            if(rating != null && platform != null){
                if(g.getRating().equals(Rating.valueOf(rating)) &&
                   g.getPlatforms().contains(Platform.valueOf(platform))){
                    result.add(g);
                    continue;
                }
                continue;
            }

            if(category != null && platform != null){
                if(g.getPlatforms().equals(Platform.valueOf(platform)) &&
                   g.getCategories().contains(Category.valueOf(category))){
                    result.add(g);
                    continue;
                }
                continue;
            }

            if(category != null){
                if(g.getCategories().contains(Category.valueOf(category))){
                    result.add(g);
                    continue;
                }
                continue;
            }

            if(rating != null){
                if(g.getRating().equals(Rating.valueOf(rating))){
                    result.add(g);
                    continue;
                }
                continue;
            }

            if(platform != null){
                if(g.getPlatforms().equals(Platform.valueOf(platform))){
                    result.add(g);
                    continue;
                }
                continue;
            }
        }

        return result;

    }

    @RequestMapping(value = "/deleteGamesFromCategory/{category}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAllGamesFromCategory(@PathVariable String category) throws NoMatchingId {
        Collection<Game> games = service.getAllGames();
        for (Game g:
             games) {
            if(g.getCategories().contains(Category.valueOf(category))){
                service.deleteGame(g);
            }
        }

        return "Delete successful!";
    }

    @RequestMapping(value = "/games/{date}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Game> getGamesByDate(@PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date){
        Collection<Game> games = service.getAllGames();
        Collection<Game> result = new ArrayList<>();
        for (Game g:
             games) {
            if(g.getRelease_date().isEqual(date)){
                result.add(g);
            }
        }
        return result;
    }

    @ExceptionHandler(FilterIsMissingException.class)
    @ResponseBody
    public String noFilterExceptionHandler(Exception ex){
        return ex.getMessage();
    }

    @ExceptionHandler(NoMatchingId.class)
    @ResponseBody
    public String noMatchingIdHandler(Exception ex){
        return "No matching id found in the database: " + ex.getMessage();
    }

}
