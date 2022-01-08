package main.schedulemanager.domain;

/**
 * This class creates Contacts objects.
 */
public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    /**
     * This is the constructor for the Contacts class.
     * @param contactId - Sets contactId.
     * @param contactName - Sets contactName.
     * @param email - Sets email.
     */
    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     *
     * @return The contactId.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     *
     * @param contactId - Sets contactId.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     *
     * @return The contactName.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     *
     * @param contactName - Sets contactName.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     *
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email - Sets email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Overides the way object is displayed to only show the contact name.
     * @return The contactName
     */
    @Override
    public String toString () {return(contactName);}
}
