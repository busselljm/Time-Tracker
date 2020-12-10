package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.UserAlreadyExistsException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;

@Service
public class JdbcUserDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT user_id, username, password_hash, role, first_name, last_name, email, avatar FROM users WHERE username ILIKE ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        if (rowSet.next()){
            return mapRowToUser(rowSet);
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password, String role, String firstName, String lastName, String email, String avatar) throws UserAlreadyExistsException {
        if (role == null || role.isEmpty()) {
            role = "ROLE_USER"; //default
        } else {
            role = "ROLE_" + role.toUpperCase();
        }
            String sql = "INSERT INTO users (username, password_hash, role, first_name, last_name, email, avatar) VALUES (?, ?, ?, ?, ?, ?, ?);";
            String password_hash = new BCryptPasswordEncoder().encode(password);
            Integer newUserId;
            try {
                return jdbcTemplate.update(sql, username, password_hash, role, firstName, lastName, email, avatar) == 1;
            } catch (DataAccessException e) {
                throw new UserAlreadyExistsException();

            }
        }




        // create user


    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setActivated(true);
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setAvatar(rs.getString("avatar"));
        return user;
    }
}
