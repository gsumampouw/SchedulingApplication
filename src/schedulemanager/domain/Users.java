package schedulemanager.domain;

/**
 * This class creates Users objects.
 */
public class Users {
    private int userId;
    private String username;
    private String password;

    /**
     * This is the constructor for the Users class.
     * @param userId - Sets userId.
     * @param username - Sets username.
     * @param password - Sets password.
     */
    public Users(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @return The userId.
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId - Sets userID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username - Sets username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password - Sets password.
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Overrides the way Users objects are displayed to only show the username.
     * @return Returns username.
     */
    @Override
    public String toString(){ return (username); }
}
