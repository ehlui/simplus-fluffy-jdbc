package noob.practising.database;

import noob.practising.helpers.PropertiesReader;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

// TODO: If it's not going to be used delete it with its tests

/**
 * Singleton class to manage database connection.
 * <p>
 * Uses as default
 */
public class DatabaseConnection {
    /**
     * Default file for database properties
     */
    private static final String propertiesFileName = "db.properties";
    /**
     * Default protocol for database
     */
    private static final String baseProtocol = "jdbc:mysql";
    /**
     * The actual Connection object to be singeltoned
     */
    private static Connection connection = null;

    private DatabaseConnection() {
    }

    /**
     * Creates a connection from {@link PropertiesReader PropertiesReader.class } helper class which read from
     * a properties file.
     *
     * @throws FileNotFoundException
     * @throws SQLException
     */
    private static void buildConnectionFromProperties() throws FileNotFoundException, SQLException {
        Map<String, String> propertiesDict = PropertiesReader.getPropertiesDict(propertiesFileName);

        if (propertiesDict == null)
            throw new FileNotFoundException("Properties file not found: '" + propertiesFileName + "'");

        String urlConn = baseProtocol + "://"
                + propertiesDict.get("db.host")
                + ":" + propertiesDict.get("db.port") + "/"
                + propertiesDict.get("db.name");


        connection = DriverManager.getConnection(
                urlConn,
                propertiesDict.get("db.user"),
                propertiesDict.get("db.password")
        );
    }

    /**
     * Creates a connection from the user input.
     * Delegate the configuration to the user.
     *
     * @param urlString Database url protocol
     * @param user      Database user
     * @param password  Database password
     * @throws SQLException {@link SQLException SQLException.class}
     */
    private static void buildConnection(String urlString, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(urlString, user, password);
    }

    /**
     * Perform the singleton method to get the Connection instance
     *
     * @return connection {@link Connection Connection.class}
     * @throws SQLException          {@link SQLException SQLException.class}
     * @throws FileNotFoundException {@link FileNotFoundException FileNotFoundException.class}
     */
    public static Connection getConnection() throws SQLException, FileNotFoundException {
        if (connection == null)
            buildConnectionFromProperties();
        return connection;
    }

    /**
     * Perform the singleton method to get the
     * Connection instance with the custom configuration for database connection.
     *
     * @param urlString Database url protocol
     * @param user      Database user
     * @param password  Database password
     * @throws SQLException          {@link SQLException SQLException.class}
     * @throws FileNotFoundException {@link FileNotFoundException FileNotFoundException.class}
     */
    public static Connection getConnection(String urlString, String user, String password) throws SQLException, FileNotFoundException {
        if (urlString == null)
            buildConnectionFromProperties();
        if (connection == null)
            buildConnection(urlString, user, password);
        return connection;
    }
}
