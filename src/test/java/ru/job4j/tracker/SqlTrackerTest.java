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

    @BeforeEach
    public void createTable() throws SQLException {
        SqlTracker tracker = new SqlTracker(connection);
        try (PreparedStatement st = connection.prepareStatement("create table if not exists items("
                + "id serial primary key,"
                + "name varchar(255),"
                + "created timestamp)")) {
            st.execute();
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement st = connection.prepareStatement("drop table items")) {
            st.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() throws SQLException {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenAddThenReplace() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        Item item2 = new Item("item2");
        tracker.replace(1, item2);
        assertThat(tracker.findById(1).getName()).isEqualTo(item2.getName());
    }

    @Test
    public void whenAddThenDelete() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.delete(1)).isTrue();
    }

    @Test
    public void whenAddThenFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        tracker.add(new Item("item"));
        tracker.add(new Item("item2"));
        List<Item> rsl = tracker.findAll();
        assertThat(rsl.size()).isEqualTo(2);
        assertThat(tracker.findById(1)).isEqualTo(rsl.get(0));
        assertThat(tracker.findById(2)).isEqualTo(rsl.get(1));
    }

    @Test
    public void whenAddThenFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item item2 = new Item("item");
        tracker.add(item);
        tracker.add(item2);
        List<Item> rsl = tracker.findByName("item");
        assertThat(rsl.size()).isEqualTo(2);
        assertThat(tracker.findByName("item").get(0).getName()).isEqualTo("item");
        assertThat(tracker.findByName("item").get(0).getId()).isEqualTo(1);
        assertThat(tracker.findByName("item").get(1).getName()).isEqualTo("item");
        assertThat(tracker.findByName("item").get(1).getId()).isEqualTo(2);
    }
}
