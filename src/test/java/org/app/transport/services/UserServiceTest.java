package org.app.transport.services;

import org.apache.commons.io.FileUtils;
import org.app.transport.FileSystemService;
import org.app.transport.UserService;
import org.app.transport.exceptions.IncorrectPassword;
import org.app.transport.exceptions.IncorrectUsername;
import org.app.transport.exceptions.RoleException;
import org.app.transport.exceptions.UsernameAlreadyExistsException;
import org.app.transport.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    public static final String ADMIN = "admin";

    @BeforeEach
    void setUp() throws IOException {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }
    @AfterEach
    void tearDown() {
        UserService.database.close();
    }
    @Test
    @DisplayName("Database is initialized, and there are no users")
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }
    @Test
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException, IncorrectUsername, IncorrectPassword, RoleException {
        UserService.addUser(ADMIN, ADMIN, ADMIN,ADMIN);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(ADMIN);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(ADMIN, ADMIN));
        assertThat(user.getRole()).isEqualTo(ADMIN);
    }
    @Test
    @DisplayName("User can not be added twice")
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(ADMIN, ADMIN, ADMIN,ADMIN);
            UserService.addUser(ADMIN, ADMIN, ADMIN,ADMIN);
        });
    }
    @Test
    @DisplayName("Update user check")
    void testUserUpdate() throws IncorrectUsername, UsernameAlreadyExistsException, IncorrectPassword, RoleException {
        UserService.addUser(ADMIN, ADMIN, ADMIN,"*");
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        user.setSomething("admin");
        UserService.updateUser(user,user.getUsername());
        assertThat(user.getGood()).isEqualTo("admin");
    }
    @Test
    @DisplayName("Find the user")
    void FindTheUser() throws IncorrectUsername, UsernameAlreadyExistsException, IncorrectPassword, RoleException {
        UserService.addUser(ADMIN, ADMIN, ADMIN,ADMIN);
        UserService.addUser(ADMIN+"ul", ADMIN, ADMIN,ADMIN);
        User user = UserService.getAllUsers().get(0);
        User user1 =UserService.FindTheUser(user.getUsername());
        assertThat(user.equals(user1));
    }
    @Test
    @DisplayName("Encode Password check")
    void EncodePassword()
    {
        String s1= UserService.encodePassword("buna","apa");
        String s2=UserService.encodePassword("buna","apa");
        assertThat(s1).isEqualTo(s2);
    }
}