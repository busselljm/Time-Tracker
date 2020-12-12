package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.UserAlreadyExistsException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash, role, first_name, last_name, email, avatar, manager_id, manager_first_name, manager_last_name " +
                "FROM users;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()) {
            User user = mapRowToUser(rowSet);
            users.add(user);
        }
        return users;
    }

    @Override
    public void updateUser(User user, String username, Long id) {
        String sql = "UPDATE users SET first_name=? , last_name =? , email =?, avatar =?, manager_id =?, manager_first_name = ?, manager_last_name = ? WHERE username=? AND user_id = ?;";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getAvatar(), user.getManagerID(), user.getManagerFirstName(), user.getManagerLastName(), username, id);
    }

    @Override
    public Long getUserIDByName(String firstName, String lastName) {
        String sqlManagerID = "SELECT user_id FROM users WHERE first_name = ? AND last_name =?;";
       return jdbcTemplate.queryForObject(sqlManagerID, Long.class, firstName, lastName);
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT user_id, username, password_hash, role, first_name, last_name, email, avatar, manager_id, manager_first_name, manager_last_name FROM users WHERE username ILIKE ?;";
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
        try {
            user.setManagerID(rs.getLong("manager_id"));
            user.setManagerFirstName(rs.getString("manager_first_name"));
            user.setManagerLastName(rs.getString("manager_last_name"));
        } catch(NullPointerException e) {
            e.getMessage();
        }
        return user;
    }
}
