package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import services.GameStoreService;

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
}
