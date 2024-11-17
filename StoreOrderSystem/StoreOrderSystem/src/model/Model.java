package model;


import java.util.Random;

public class Model {

    private DatabaseManager databaseManager = new DatabaseManager();
    private String overrideCode;


    public Model()
    {
        overrideCode = randomizeOverrideCode();
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public String getOverrideCode() {
        return overrideCode;
    }

    public String randomizeOverrideCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        // Generate 8 random characters
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }
}