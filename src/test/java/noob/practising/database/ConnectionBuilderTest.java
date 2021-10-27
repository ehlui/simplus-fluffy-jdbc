package noob.practising.database;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionBuilderTest {

    @Mock
    private ConnectionBuilder connectionBuilder;

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Before
    public void before() {
        connectionBuilder = new ConnectionBuilder();
    }


    @Test(expected = SQLException.class)
    public void testDatabaseConnectionWithoutSettingAnyProperties() throws Exception {
        connectionBuilder.getConnection();
    }

    @Test
    public void testDatabaseConnectionFromPropertiesFile() throws Exception {
        int returnValue = 1;
        String propertiesTestFile = "test.properties";

        ConnectionBuilder connectionBuilder = Mockito.mock(ConnectionBuilder.class, Mockito.RETURNS_DEEP_STUBS);

        Mockito.when(connectionBuilder.setProperties(Mockito.anyString()).getConnection()).thenReturn(mockConnection);
        Connection connection = connectionBuilder.setProperties(propertiesTestFile).getConnection();

        Mockito.when(connection.createStatement()).thenReturn(mockStatement);
        Mockito.when(connection.createStatement().executeUpdate(Mockito.any())).thenReturn(returnValue);

        Statement mockStatement = connection.createStatement();
        int expectedValue = mockStatement.executeUpdate("insert into director values (1,'Lele','China');");

        Assert.assertEquals(returnValue, expectedValue);
    }

    @Test
    public void testDatabaseConnectionFromSetters() throws Exception {
        int returnValue = 1;
        ConnectionBuilder connectionBuilder = Mockito.mock(ConnectionBuilder.class, Mockito.RETURNS_DEEP_STUBS);

        Mockito.when(connectionBuilder
                .setDbName(Mockito.anyString())
                .setHost(Mockito.anyString())
                .setPort(Mockito.anyString())
                .setProperties(Mockito.anyString())
                .setProtocol(Mockito.anyString())
                .setCredentials(Mockito.anyString(), Mockito.anyString())
                .getConnection()).thenReturn(mockConnection);

        Connection connection = connectionBuilder
                .setDbName(Mockito.anyString())
                .setHost(Mockito.anyString())
                .setPort(Mockito.anyString())
                .setProperties(Mockito.anyString())
                .setProtocol(Mockito.anyString())
                .setCredentials(Mockito.anyString(), Mockito.anyString())
                .getConnection();

        Mockito.when(connection.createStatement()).thenReturn(mockStatement);
        Mockito.when(connection.createStatement().executeUpdate(Mockito.any())).thenReturn(returnValue);

        Statement mockStatement = connection.createStatement();
        int expectedValue = mockStatement.executeUpdate("insert into director values (1,'Simon','España');");


        Assert.assertEquals(returnValue, expectedValue);
    }

}
