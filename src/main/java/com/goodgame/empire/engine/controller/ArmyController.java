package com.goodgame.empire.engine.controller;

import com.goodgame.empire.engine.api.GoodGameEngine;
import com.goodgame.empire.engine.model.Army;
import com.goodgame.empire.engine.model.TroopRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ArmyController {

    @Autowired
    private GoodGameEngine goodGameEngine;

    /**
     * v1 is o(1) solution
     */
    @PostMapping("/army/v1")
    public ResponseEntity<?> getArmyNumbers(@RequestBody TroopRequest troopRequest) {
        if (troopRequest.getTroopNum() < 3)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Input should not be less than 3");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(goodGameEngine.startGame(troopRequest.getTroopNum()));
    }

    /**
     * v2 is o(n) solution
     */
    @PostMapping("/army/v2")
    public ResponseEntity<?> getArmies(@RequestBody TroopRequest troopRequest) {
        if (troopRequest.getTroopNum() < Army.class.getDeclaredFields().length)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Input should not be less than " + Army.class.getDeclaredFields().length);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(goodGameEngine.startGame(troopRequest));
    }
}
