package com.game.asteroids;

public class GameObjectUpdateRequest {
    public final GameObject GAME_OBJECT;
    public final GameObjectOperation OPERATION;

    public GameObjectUpdateRequest(GameObject gameObject, GameObjectOperation operation) {
        GAME_OBJECT = gameObject;
        OPERATION = operation;
    }
}
