package fr.oxidayzz.uhc.managers;

public enum GameStateManager {
    LOBBY,
    STARTING,
    INGAME,
    END;


    private static GameStateManager currentPhase = LOBBY;

    public static boolean isGame(GameStateManager phase) {
        return currentPhase == phase;
    }

    public static void setCurrentPhase(GameStateManager newGamePhase) {
        currentPhase = newGamePhase;
    }

    public static GameStateManager getGamePhase() {
        return currentPhase;
    }


}
