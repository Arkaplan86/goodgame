package com.goodgame.empire.engine.api;

import com.goodgame.empire.engine.model.Army;
import com.goodgame.empire.engine.model.TroopRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Random;

@Slf4j
@Service
public class GoodGameEngineImpl implements GoodGameEngine {

    @SneakyThrows
    @Override
    public Army startGame(int num) {
        try {
            Army army = new Army();
            Random random = new Random();

            int n1 = random.nextInt(num - 2) + 1;
            int n2 = random.nextInt(num - n1 - 1) + 1;
            int n3 = num - n1 - n2;

            army.setArchers(n1);
            army.setSpearMan(n2);
            army.setSwordsMan(n3);

            return army;
        } catch (Exception ex) {
            log.error("exception message : ", ex);
            throw new Exception(ex);
        }
    }

    @SneakyThrows
    @Override
    public Army startGame(TroopRequest troopRequest) {

        try {
            Army army = new Army();
            Random random = new Random();
            Field[] troopFields = army.getClass().getDeclaredFields();

            int remainingTroopNumber = troopRequest.getTroopNum();
            int remainingTroopSize = troopFields.length;
            int sum = 0;

            /**
             * if input number equals troop numbers, so every troop should be 1 and
             */
            if (remainingTroopNumber - remainingTroopSize == 0 || remainingTroopNumber - remainingTroopSize == 1) {
                for (Field field : troopFields) {
                    field.setAccessible(true);
                    field.set(army, 1);
                }
            }

            if (remainingTroopNumber - remainingTroopSize == 1) {
                int result = random.nextInt(troopFields.length);
                Field field = troopFields[result];
                field.setAccessible(true);
                field.set(army, 2);
            }

            if (remainingTroopNumber - remainingTroopSize > 1) {
                for (int index = 0; index < troopFields.length - 1; index++) {
                    int result = random.nextInt(remainingTroopNumber - remainingTroopSize) + 1;
                    remainingTroopNumber = remainingTroopNumber - result;
                    remainingTroopSize = remainingTroopSize - 1;
                    sum = sum + result;
                    Field field = troopFields[index];
                    field.setAccessible(true);
                    field.set(army, result);
                }

                int finalTroopNumber = troopRequest.getTroopNum() - sum;
                Field finalField = troopFields[troopFields.length - 1];
                finalField.setAccessible(true);
                finalField.set(army, finalTroopNumber);
            }

            return army;

        } catch (Exception ex) {
            log.error("exception message : ", ex);
            throw new Exception(ex);
        }
    }
}
