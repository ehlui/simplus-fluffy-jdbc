package noob.practising.database;

public interface Builder {
    Object setDbName(String dbName);
    Object setHost(String host);
    Object setPort(String port);
    Object setProperties(String properties);
    Object setProtocol(String dbProtocol);
    Object setCredentials(String user,String password);
}
