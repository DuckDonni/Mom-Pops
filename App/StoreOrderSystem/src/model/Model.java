package model;


import java.util.Random;

/**
 * Represents the core model of the application, managing the database interaction
 * and providing functionalities like generating override codes.
 *
 * <p>This class acts as a bridge between the database layer and other components,
 * encapsulating operations related to the {@link DatabaseManager} and providing
 * an override code system for authorizing orders of 10 or more pizzas.</p>
 */
public class Model {

    private DatabaseManager databaseManager = new DatabaseManager();
    private String overrideCode;

    /**
     * Initializes the Model class, including generating a random override code.
     */
    public Model()
    {
        overrideCode = randomizeOverrideCode();
    }

    /**
     * Retrieves the instance of {@link DatabaseManager}.
     *
     * @return The {@link DatabaseManager} associated with this model.
     */
    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    /**
     * Retrieves the current override code.
     *
     * @return The override code as a {@link String}.
     */
    public String getOverrideCode() {
        return overrideCode;
    }

    /**
     * Generates a new random override code consisting of 8 alphanumeric characters.
     *
     * @return A randomly generated override code as a {@link String}.
     */
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