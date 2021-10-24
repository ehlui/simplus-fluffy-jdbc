package noob.practising.database;

import noob.practising.helpers.PropertiesReader;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Connection Builder class for a custom DriverManager {@link DriverManager DriverManager.class } wrapper
 * <p>
 * The connection can be built by to ways:
 * <ol>
 *     <li>Using a properties file with {@link ConnectionBuilder#setProperties}</li>
 *     <li>Using the builder setters</li>
 * </ol>
 */
public class ConnectionBuilder implements Builder {
    /**
     * <b>(Optional attribute)</b>
     * File which properties will be read from
     * It will be used for {@link PropertiesReader PropertiesReader.class}
     */
    private String properties;
    /**
     * Database name
     */
    private String dbName;
    /**
     * Database protocol to perform connection
     * i.e. "jdbc:mysql"  | "jdbc:database"
     */
    private String dbProtocol;
    /**
     * The actual host (Localhost, ip)
     */
    private String hostName;
    /**
     * The actual host (Localhost, ip)
     */
    private String port;
    /**
     * Database user for establishing connection
     */
    private String user;
    /**
     * Database password for establishing connection
     */
    private String password;
    /**
     * Database connection instance
     */
    private Connection connection;

    @Override
    public ConnectionBuilder setDbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    @Override
    public ConnectionBuilder setHost(String host) {
        this.hostName = host;
        return this;
    }

    @Override
    public ConnectionBuilder setPort(String port) {
        this.port = port;
        return this;
    }

    @Override
    public ConnectionBuilder setProperties(String properties) {
        this.properties = properties;
        return this;
    }

    @Override
    public ConnectionBuilder setProtocol(String dbProtocol) {
        this.dbProtocol = dbProtocol;
        return this;
    }

    @Override
    public ConnectionBuilder setCredentials(String user, String password) {
        this.user = user;
        this.password = password;
        return this;
    }

    /**
     * Creates a connection from {@link PropertiesReader PropertiesReader.class } helper class which read from
     * a properties file.
     *
     * @throws FileNotFoundException
     * @throws SQLException
     */
    private void buildConnectionFromProperties() throws FileNotFoundException, SQLException {
        Map<String, String> propertiesDict = PropertiesReader.getPropertiesDict(properties);

        if (propertiesDict == null)
            throw new FileNotFoundException("Properties file not found: '" + properties + "'");

        String urlConn = propertiesDict.get("db.protocol") + "://"
                + propertiesDict.get("db.host")
                + ":" + propertiesDict.get("db.port") + "/"
                + propertiesDict.get("db.name");


        this.connection = DriverManager.getConnection(
                urlConn,
                propertiesDict.get("db.user"),
                propertiesDict.get("db.password")
        );
    }

    private void buildConnection() throws SQLException {
        String urlConn = dbProtocol + "://"
                + this.hostName
                + ":" + this.port + "/"
                + this.dbName;
        this.connection = DriverManager.getConnection(urlConn, this.user, this.password);
    }

    /**
     * @return connection {@link Connection Connection.class}
     */
    public Connection getConnection() throws SQLException, FileNotFoundException {
        if (this.properties != null)
            buildConnectionFromProperties();
        else
            buildConnection();
        return this.connection;
    }
}
