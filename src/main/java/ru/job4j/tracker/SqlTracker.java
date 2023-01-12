package ru.job4j.tracker;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    private void init() {
        try (InputStream in = new FileInputStream("db/liquibase.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement st = cn.prepareStatement(
                "insert into items (name, created) values(?,?)")) {
            st.setString(1, item.getName());
            st.setTimestamp(2, Timestamp.valueOf(item.getDateTime()));
            st.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (PreparedStatement st = cn.prepareStatement("update items set name = ?, created = ? where id = ?")) {
            st.setString(1, item.getName());
            st.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            st.setInt(3, id);
            rsl = st.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement st = cn.prepareStatement("delete from items where id = ?")) {
            st.setInt(1, id);
            rsl = st.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> l = new ArrayList<>();
        try (PreparedStatement st = cn.prepareStatement("select * from items")) {
            try (ResultSet set = st.executeQuery()) {
                while (set.next()) {
                    Item item = new Item();
                    item.setId(set.getInt("id"));
                    item.setName(set.getString("name"));
                    item.setCreated(Timestamp.valueOf(set.getString("created")).toLocalDateTime());
                    l.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> l = new ArrayList<>();
        try (PreparedStatement st = cn.prepareStatement("select * from items where name = ?")) {
            st.setString(1, key);
            try (ResultSet set = st.executeQuery()) {
                if (set.next()) {
                    Item item = new Item();
                    item.setId(set.getInt("id"));
                    item.setName(set.getString("name"));
                    item.setCreated(Timestamp.valueOf(set.getString("created")).toLocalDateTime());
                    l.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public Item findById(int id) {
        Item rsl = new Item();
        try (PreparedStatement st = cn.prepareStatement("select * from items where id = ?")) {
            st.setInt(1, id);
            try (ResultSet set = st.executeQuery()) {
                if (set.next()) {
                    rsl.setId(set.getInt("id"));
                    rsl.setName(set.getString("name"));
                    rsl.setCreated(Timestamp.valueOf(set.getString("created")).toLocalDateTime());
                } else {
                    rsl = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(rsl);
        return rsl;
    }
}