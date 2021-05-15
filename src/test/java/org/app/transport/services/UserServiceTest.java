package org.app.transport.services;

import org.apache.commons.io.FileUtils;
import org.app.transport.exceptions.IncorrectPassword;
import org.app.transport.exceptions.IncorrectUsername;
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
}