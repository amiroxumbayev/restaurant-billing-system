package com.example.restaurantbillingsystem.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.restaurantbillingsystem.Menu;
import com.example.restaurantbillingsystem.Menu.Type;

@Repository
public class JdbcMenuRepository implements MenuRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMenuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Menu> findAll() {
        return jdbcTemplate.query("select * from public.menu", this::mapRowToMenu);
    }

    @Override
    public Optional<Menu> findById(int id) {
        List<Menu> results = jdbcTemplate.query("select id, title,price, type from public.Menu where id=?",
                this::mapRowToMenu, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    private Menu mapRowToMenu(ResultSet row, int rownum) throws SQLException {
        return new Menu(row.getLong("id"), row.getString("title"), row.getDouble("price"),
                Type.valueOf(row.getString("type").toUpperCase()));
    }

    @Override
    public Menu save(Menu menu) {
        jdbcTemplate.update("insert into public.Menu (title, price, type) values (?, ?, ?)", menu.getTitle(),
                menu.getPrice(), menu.getType().toString());
        return menu;
    }

    @Override
    public int update(int id, Menu menu) {
        String sql = "update public.menu set title='" + menu.getTitle() + "', price=" + menu.getPrice() + ",type='"
                + menu.getType().toString() + "' where id=" + id + "";
        return jdbcTemplate.update(sql);
    }

    @Override
    public int delete(int id) {
        String sql = "delete from public.menu where id=" + id;
        return jdbcTemplate.update(sql);
    }
}
