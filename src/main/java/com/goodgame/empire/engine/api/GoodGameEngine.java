package com.goodgame.empire.engine.api;

import com.goodgame.empire.engine.model.Army;
import com.goodgame.empire.engine.model.TroopRequest;

public interface GoodGameEngine {

    Army startGame(int num);

    Army startGame(TroopRequest troopRequest);
}
