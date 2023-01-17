package ru.job4j.tracker;

import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = new FileInputStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement st = connection.prepareStatement("delete from items")) {
            st.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item"));
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenAddThenReplace() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item"));
        Item item2 = new Item("item2");
        tracker.replace(item.getId(), item2);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(item2.getName());
    }

    @Test
    public void whenAddThenDelete() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item"));
        assertThat(tracker.delete(item.getId())).isTrue();
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenAddThenFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item"));
        Item item2 = tracker.add(new Item("item2"));
        assertThat(tracker.findAll().size()).isEqualTo(2);
        assertThat(tracker.findAll()).isEqualTo(List.of(item, item2));
    }

    @Test
    public void whenAddThenFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item"));
        Item item2 = tracker.add(new Item("item"));
        assertThat(tracker.findByName("item").size()).isEqualTo(2);
        assertThat(tracker.findByName("item")).isEqualTo(List.of(item, item2));
    }
}