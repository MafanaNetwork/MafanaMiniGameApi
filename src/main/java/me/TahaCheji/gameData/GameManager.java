package me.TahaCheji.gameData;

import java.io.IOException;

public interface GameManager {

    void playerJoin(GamePlayer gamePlayer);
    void start();
    void end();
    void save() throws IOException;
    void playerLeave(GamePlayer gamePlayer);
    void resetGameInfo();
    void setWinner(GamePlayer gamePlayer);
    void assignSpawnPositions();
    void startCountDown();

}
