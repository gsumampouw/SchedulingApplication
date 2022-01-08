package schedulemanager.domain;

import java.time.LocalDateTime;

/**
 * This class creates appointment objects.
 */
public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;
    private int userId;
    private int contactId;

    /**
     * This is a contructor for the appointment class.
     *
     * @param appointmentId - Sets appointmentId.
     * @param title         - Sets title.
     * @param description   - Sets description.
     * @param location      - Sets location.
     * @param type          - Sets type.
     * @param start         - Sets start.
     * @param end           - Sets end.
     * @param customerId    - Sets customerId.
     * @param userId        - Sets userId.
     * @param contactId     - Sets contactId.
     */
    public Appointments(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * @return The appointmentId.
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId - Sets appointmentId.
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return The Title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title - Sets title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description - Sets description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location - Sets location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return The type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type - Sets type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The start.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * @param start - Sets start.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return The end.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * @param end - Sets end.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * @return The customerId.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId - Sets customerId.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return The userId.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId - Sets userId.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return The contactId.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId - Sets contactId.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
