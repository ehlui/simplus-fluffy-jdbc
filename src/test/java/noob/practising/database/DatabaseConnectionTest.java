package noob.practising.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito; // Less "Visual for me" but less boileplate though -> //import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class DatabaseConnectionTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;


    @Test
    public void testMockDatabaseConnectionWithParams() throws Exception {
        try (MockedStatic<DatabaseConnection> databaseConnection = Mockito.mockStatic(DatabaseConnection.class)) {
            databaseConnection
                    .when(
                            () -> DatabaseConnection.getConnection(
                                    "jdbc:mysql://localhost", "user", "password")
                    ).thenReturn(mockConnection);

            Assert.assertEquals(
                    DatabaseConnection
                            .getConnection(
                                    "jdbc:mysql://localhost", "user", "password"), mockConnection);
        }
    }

    @Test
    public void testMockDatabaseConnectionWithoutParams() throws Exception {
        //Mocking our connection
        try (MockedStatic<DatabaseConnection> databaseConnection = Mockito.mockStatic(DatabaseConnection.class)) {
            databaseConnection.when(DatabaseConnection::getConnection).thenReturn(mockConnection);
            Assert.assertEquals(DatabaseConnection.getConnection(), mockConnection);
        }
    }

    @Test
    public void testMockDatabaseConnectionInsertValue() throws Exception {
        int returnValue = 1;

        try (MockedStatic<DatabaseConnection> databaseConnection = Mockito.mockStatic(DatabaseConnection.class)) {
            databaseConnection.when(DatabaseConnection::getConnection).thenReturn(mockConnection);
            Connection mockedConnection = DatabaseConnection.getConnection();

            Mockito.when(mockedConnection.createStatement()).thenReturn(mockStatement);
            Mockito.when(mockedConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(returnValue);

            Statement mockStatement = mockedConnection.createStatement();
            int expectedValue = mockStatement.executeUpdate("insert into director values (1,'Lele','China');");

            Assert.assertEquals(expectedValue, returnValue);
        }
    }

    @Test
    public void testMockDatabaseConnectionGetValues() throws Exception {

        //Mocking our ResultSet

        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.getInt("id")).thenReturn(1);
        Mockito.when(resultSetMock.getString("name")).thenReturn("Lele");
        Mockito.when(resultSetMock.getString("nationality")).thenReturn("China");

        //Mocking our DatabaseConnection.getConnection method with the previous mock
        try (MockedStatic<DatabaseConnection> databaseConnection = Mockito.mockStatic(DatabaseConnection.class)) {

            databaseConnection.when(DatabaseConnection::getConnection).thenReturn(mockConnection);
            Connection mockedConnection = DatabaseConnection.getConnection();

            Mockito.when(mockedConnection.createStatement()).thenReturn(mockStatement);
            Mockito.when(mockedConnection.createStatement().executeQuery(Mockito.any())).thenReturn(resultSetMock);

            Statement mockStatement = mockedConnection.createStatement();
            ResultSet resultSet = mockStatement.executeQuery("SELECT * FROM director where id = 1");

            Assert.assertEquals(resultSet.getString("name"), "Lele");
            Assert.assertEquals(resultSet.getString("nationality"), "China");
            Assert.assertEquals(resultSet.getInt("id"), 1);
        }
    }
}
