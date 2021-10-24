package noob.practising.dao;

import noob.practising.database.ConnectionBuilder;
import noob.practising.database.DatabaseConnection;
import noob.practising.model.Director;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>A Persisting Layer Class for managing {@link Director Director.class} in a database.</p>
 * <p>It is implemented the 4 main methods from {@link Dao Dao.interface} interface</p>
 */
public class DirectorDao implements Dao<Director> {

    private String propertiesFile;

    public DirectorDao setProperties(String propertiesFile) {
        if (propertiesFile == null)
            throw new NullPointerException("PropertiesFile String is null, we cannot read a null object");
        this.propertiesFile = propertiesFile;
        return this;
    }

    private Connection getConnection() throws SQLException, FileNotFoundException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilder();
        return connectionBuilder.setProperties(this.propertiesFile).getConnection();
    }

    @Override
    public Director find(int id) {

        if (id < 1)
            return null;

        Director director = null;
        String preparedStmt = "SELECT * FROM director where id = ?;";

        try (PreparedStatement pst = getConnection().prepareStatement(preparedStmt);
        ) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (rs.isLast())
                director = new Director(rs.getInt(1), rs.getString(2));
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return director;
    }

    @Override
    public List<Director> find() {
        List<Director> directors = null;

        try (Statement st = getConnection().createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM director; ");) {
            directors = new ArrayList<>();
            while (rs.next())
                directors.add(new Director(rs.getInt(1), rs.getString(2), rs.getString(3)));
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return directors;
    }

    @Override
    public void add(Director director) {

        if (director == null)
            return;

        String preparedStmt = "INSERT INTO director (name,nationality) VALUES (?,?);";
        try (PreparedStatement pst = getConnection().prepareStatement(preparedStmt)
        ) {
            pst.setString(1, director.getName());
            pst.setString(2, director.getNationality());
            int rowsAffected = pst.executeUpdate();

            System.out.println("ADD: " + rowsAffected);
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Director director) {
        String preparedStmt = "UPDATE director SET name = ?, nationality = ? WHERE id = ?;";
        try (PreparedStatement pst = getConnection().prepareStatement(preparedStmt)
        ) {
            pst.setString(1, director.getName());
            pst.setString(2, director.getNationality());
            pst.setInt(3, director.getId());
            int rowsAffected = pst.executeUpdate();

            System.out.println("update: " + rowsAffected);
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Director director) {
        if (director == null)
            return;

        String preparedStmt = "DELETE FROM director WHERE id = ?;";
        try (PreparedStatement pst = getConnection().prepareStatement(preparedStmt)
        ) {
            pst.setInt(1, director.getId());
            int rowsAffected = pst.executeUpdate();

            System.out.println("remove: " + rowsAffected);
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
