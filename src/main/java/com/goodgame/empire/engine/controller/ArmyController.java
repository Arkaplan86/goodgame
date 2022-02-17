package com.goodgame.empire.engine.controller;

import com.goodgame.empire.engine.api.GoodGameEngine;
import com.goodgame.empire.engine.model.Army;
import com.goodgame.empire.engine.model.TroopRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.goodgame.empire.engine.constant.Constants.LIMIT;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ArmyController {

    private final GoodGameEngine goodGameEngine;

    /**
     * v1 is o(1) solution
     */
    @PostMapping("/army/v1")
    public ResponseEntity<?> getArmiesV1(@RequestBody TroopRequest troopRequest) {
        if (troopRequest.getTroopNum() < LIMIT)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Input should not be less than " + LIMIT);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(goodGameEngine.startGame(troopRequest.getTroopNum()));
    }

    /**
     * v2 is o(n) solution
     */
    @PostMapping("/army/v2")
    public ResponseEntity<?> getArmiesV2(@RequestBody TroopRequest troopRequest) {
        if (troopRequest.getTroopNum() < Army.class.getDeclaredFields().length)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Input should not be less than " + Army.class.getDeclaredFields().length);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(goodGameEngine.startGame(troopRequest));
    }
}
