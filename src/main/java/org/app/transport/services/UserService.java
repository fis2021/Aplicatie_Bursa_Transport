package org.app.transport.services;

import org.app.transport.exceptions.IncorrectPassword;
import org.app.transport.exceptions.IncorrectUsername;
import org.app.transport.exceptions.RoleException;
import org.app.transport.exceptions.UsernameAlreadyExistsException;
import org.app.transport.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.RemoveOptions;
import org.dizitart.no2.exceptions.NitriteIOException;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import static org.app.transport.services.FileSystemService.getPathToFile;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class UserService {
    public static ObjectRepository<User> userRepository;
    public static Nitrite database;
    public static void initDatabase() {
        FileSystemService.initDirectory();
             database = Nitrite.builder()
                    .filePath(getPathToFile("myData7.db").toFile())
                    .openOrCreate("test", "test");
            userRepository = database.getRepository(User.class);

    }
    public static void addUser(String username, String password, String role, String good) throws UsernameAlreadyExistsException, IncorrectUsername, IncorrectPassword, RoleException {
        checkUserDoesNotAlreadyExist(username);
        if(username.length()<3) throw new IncorrectUsername();
        if(password.length()<3) throw new IncorrectPassword();
        if(role==null) throw new RoleException();
        userRepository.insert(new User(username, encodePassword(username, password), role,good));
    }
    public static List<User> getAllUsers() {
        return userRepository.find().toList();
    }
    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }
    public static void updateUser(User c,String username)
    {
        userRepository.update(eq("username",username),c);
       // User p=new User(c.getUsername(),c.getPassword(),c.getRole(),c.getGood()+"/"+good);
        //userRepository.remove(eq("username",username));
        //userRepository.insert(p);
    }

    public static void updateStatus(User c, String state)
    {
        userRepository.update(eq("offerState", state),c);
    }

    public static boolean checkIsInDataBase(String username){
        boolean b=false;
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                b=true;
        }
        return b;
    }
    public static User FindTheUser(String username){
        User a=new User();
            for (User user : userRepository.find()) {
                if (Objects.equals(username, user.getUsername()))
                    a=user;
            }
            return a;
    }
    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
