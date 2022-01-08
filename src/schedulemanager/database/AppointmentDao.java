package schedulemanager.database;

import schedulemanager.domain.Appointments;
import schedulemanager.domain.TotalApptbyMonth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.*;

import static schedulemanager.database.JDBC.*;

// TODO: Refactor all database package classes to match DAO naming convention e.g.. AppointmentsDao
public abstract class AppointmentsTable {

    public static final String DESCRIPTION = "description";
    public static final String APPOINTMENT_ID = "appointment_id";
    public static final String TITLE = "title";
    public static final String LOCATION = "location";
    public static final String TYPE = "type";
    public static final String START = "start";
    public static final String END = "end";
    public static final String USER_ID = "user_id";
    public static final String CONTACT_ID = "contact_Id";
    public static final String CUSTOMER_ID = "customer_Id";
    public static final String SELECT_FROM_APPOINTMENTS_WHERE_CUSTOMER_ID = "SELECT * FROM appointments WHERE Customer_ID = ";

    /**
     * Gets all associated appointments based on the customer ID.
     * @param customerId - customer ID
     * @return Returns an observable list of associated appointments.
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllAssociatedAppt(int customerId) throws SQLException {
        ObservableList<Appointments> allAssociatedAppt = FXCollections.observableArrayList();
        Connection connection = openConnection();

        Statement stmnt;
        String sqlStatement = SELECT_FROM_APPOINTMENTS_WHERE_CUSTOMER_ID + customerId + ";";
        try {
            stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int appointmentId = result.getInt(APPOINTMENT_ID);
                String title = result.getString(TITLE);
                String description = result.getString(DESCRIPTION);
                String location = result.getString(LOCATION);
                String type = result.getString(TYPE);
                Timestamp timeStampStart = result.getTimestamp(START);
                LocalDateTime start = timeStampStart.toLocalDateTime();
                Timestamp timeStampEnd = result.getTimestamp(END);
                LocalDateTime end = timeStampEnd.toLocalDateTime();
                int userId = result.getInt(USER_ID);
                int contactId = result.getInt(CONTACT_ID);

                Appointments appt = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                allAssociatedAppt.add(appt);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return allAssociatedAppt;


    }

    /**
     * Gets associated appointments for the next month based on customer Id.
      * @param customerId - Customer ID.
     * @return Returns an observable list of associated appointments.
     * @throws SQLException
     */
    public static ObservableList<Appointments> getMonthAssociatedAppt(int customerId) throws SQLException {
        ObservableList<Appointments> allAssociatedAppt = FXCollections.observableArrayList();
        Connection connection = openConnection();


        String sqlStatement = SELECT_FROM_APPOINTMENTS_WHERE_CUSTOMER_ID + customerId + " AND start between now() and date_add(now(), interval 1 month);";
        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int appointmentId = result.getInt(APPOINTMENT_ID);
                String title = result.getString(TITLE);
                String description = result.getString(DESCRIPTION);
                String location = result.getString(LOCATION);
                String type = result.getString(TYPE);
                Timestamp timeStampStart = result.getTimestamp(START);
                LocalDateTime start = timeStampStart.toLocalDateTime();
                Timestamp timeStampEnd = result.getTimestamp(END);
                LocalDateTime end = timeStampEnd.toLocalDateTime();
                int userId = result.getInt(USER_ID);
                int contactId = result.getInt(CONTACT_ID);

                Appointments appt = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                allAssociatedAppt.add(appt);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return allAssociatedAppt;
    }

    /**
     * Gets the associated appointments for the next week based on customer ID.
     * @param customerId - Customer ID.
     * @return Returns an observable list of appointments.
     * @throws SQLException
     */
    public static ObservableList<Appointments> getWeekAssociatedAppt(int customerId) throws SQLException {
        ObservableList<Appointments> allAssociatedAppt = FXCollections.observableArrayList();
        Statement stmnt = null;
        Connection connection = openConnection();

        String sqlStatement = SELECT_FROM_APPOINTMENTS_WHERE_CUSTOMER_ID + customerId + " AND start between now() and date_add(now(), interval 1 week);";
        try {
            stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int appointmentId = result.getInt(APPOINTMENT_ID);
                String title = result.getString(TITLE);
                String description = result.getString(DESCRIPTION);
                String location = result.getString(LOCATION);
                String type = result.getString(TYPE);
                Timestamp timeStampStart = result.getTimestamp(START);
                LocalDateTime start = timeStampStart.toLocalDateTime();
                Timestamp timeStampEnd = result.getTimestamp(END);
                LocalDateTime end = timeStampEnd.toLocalDateTime();
                int userId = result.getInt(USER_ID);
                int contactId = result.getInt(CONTACT_ID);

                Appointments appt = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                allAssociatedAppt.add(appt);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return allAssociatedAppt;


    }

    /**
     * Gets all appointments in the Appointments table in the database.
      * @return Returns an observable list.
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllAppt() throws SQLException {

        ObservableList<Appointments> allAppt = FXCollections.observableArrayList();
        Statement stmnt = null;
        Connection connection = openConnection();

        String sqlStatement = "SELECT * FROM appointments;";
        try {
            stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int customerId = result.getInt(CUSTOMER_ID);
                int appointmentId = result.getInt(APPOINTMENT_ID);
                String title = result.getString(TITLE);
                String description = result.getString(DESCRIPTION);
                String location = result.getString(LOCATION);
                String type = result.getString(TYPE);
                Timestamp timeStampStart = result.getTimestamp(START);
                LocalDateTime start = timeStampStart.toLocalDateTime();
                Timestamp timeStampEnd = result.getTimestamp(END);
                LocalDateTime end = timeStampEnd.toLocalDateTime();
                int userId = result.getInt(USER_ID);
                int contactId = result.getInt(CONTACT_ID);

                Appointments appt = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                allAppt.add(appt);

            }

        } catch (SQLException throwables) {
            // TODO: Exception handling guide:
            throwables.printStackTrace();
        } finally {
            stmnt.close();
            closeConnection(connection);
        }
        return allAppt;

    }

    /**
     * Gets all appointments with in the next month from the Appointments table in the database.
      * @return Returns an observable list of appointments.
     * @throws SQLException
     */
    public static ObservableList<Appointments> getMonthAppt() throws SQLException {

        ObservableList<Appointments> monthAppt = FXCollections.observableArrayList();
        Statement stmnt = null;
        Connection connection = openConnection();

        String sqlStatement = "SELECT * FROM appointments WHERE start between now() and date_add(now(), interval 1 month);";
        try {
            stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int customerId = result.getInt(CUSTOMER_ID);
                int appointmentId = result.getInt(APPOINTMENT_ID);
                String title = result.getString(TITLE);
                String description = result.getString(DESCRIPTION);
                String location = result.getString(LOCATION);
                String type = result.getString(TYPE);
                Timestamp timeStampStart = result.getTimestamp(START);
                LocalDateTime start = timeStampStart.toLocalDateTime();
                Timestamp timeStampEnd = result.getTimestamp(END);
                LocalDateTime end = timeStampEnd.toLocalDateTime();
                int userId = result.getInt(USER_ID);
                int contactId = result.getInt(CONTACT_ID);


                Appointments appt = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                monthAppt.add(appt);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            stmnt.close();
            closeConnection(connection);
        }
        return monthAppt;

    }

    /**
     * Gets all appointments for the next week from the Appointments table in the database.
     * @return Returns an observable list of appointments.
     * @throws SQLException
     */
    public static ObservableList<Appointments> getWeekAppt() throws SQLException {

        ObservableList<Appointments> weekAppt = FXCollections.observableArrayList();
        Statement stmnt = null;
        Connection connection = openConnection();

        String sqlStatement = "SELECT * FROM appointments WHERE start between now() and date_add(now(), interval 1 week);";
        try {
            stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int customerId = result.getInt(CUSTOMER_ID);
                int appointmentId = result.getInt(APPOINTMENT_ID);
                String title = result.getString(TITLE);
                String description = result.getString(DESCRIPTION);
                String location = result.getString(LOCATION);
                String type = result.getString(TYPE);
                Timestamp timeStampStart = result.getTimestamp(START);
                LocalDateTime start = timeStampStart.toLocalDateTime();
                Timestamp timeStampEnd = result.getTimestamp(END);
                LocalDateTime end = timeStampEnd.toLocalDateTime();
                int userId = result.getInt(USER_ID);
                int contactId = result.getInt(CONTACT_ID);


                Appointments appt = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                weekAppt.add(appt);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            stmnt.close();
            closeConnection(connection);
        }
        return weekAppt;

    }

    /**
     * Creates an observable list of time starting from a local time of 00:00 to 23:00 in 1 hour increments
     * @return Returns an observable list of time.
     */
    public static ObservableList<LocalTime> listOfTime() {

        ObservableList<LocalTime> localTimes = FXCollections.observableArrayList();
        LocalTime time = LocalTime.of(0, 0);

        for (int i = 0; i < 24; i++) {
            localTimes.add(time);
            time = time.plusHours(1);
        }

        return localTimes;
    }

    /**
     * Checks if start and end time is with in EST business hours of 8AM to 10PM.  Appointments must be in the same day to be true.
     * @param start Start time of appointment.
     * @param end End time of appointment.
     * @return Returns a boolean value of  true if appointment is in office hours and false if it is not.
     */
    public static boolean checkIfTimeIsInOfficeHrs(LocalDateTime start, LocalDateTime end) {

        ZoneId estZoneID = ZoneId.of("US/Eastern");
        ZoneId localZoneID = ZoneId.systemDefault();
        ZonedDateTime startLocalTime = start.atZone(localZoneID);
        ZonedDateTime endLocalTime = end.atZone(localZoneID);


        LocalDate startDate = start.toLocalDate();
        LocalTime officeStartTime = LocalTime.of(8, 0);
        LocalTime officeEndTime = LocalTime.of(22, 0);
        ZonedDateTime officeZonedStartTime = ZonedDateTime.of(startDate, officeStartTime, estZoneID);

        ZonedDateTime officeZonedEndTime = ZonedDateTime.of(startDate, officeEndTime, estZoneID);


        if (startLocalTime.isBefore(officeZonedStartTime) || startLocalTime.isAfter(officeZonedEndTime) || endLocalTime.isAfter(officeZonedEndTime)) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Checks if the updated appointment time overlaps with other appointments.
     * @param start Start time of appointment.
     * @param end End time of appointment.
     * @param apptId Appointment ID of selected appointment.
     * @return Returns true if the start or end time of the appointment overlaps another appointment, and returns false if it does not.
     */
    public static boolean checkIfUpdatedApptOverlapsWithOtherAppts(LocalDateTime start, LocalDateTime end, int apptId) {
        /*query all appts and store appointments into an array. create for loop that check each start and end date.
        if boolean check value is false.
        it is not the current appointment id.
          the currentstart  is after the existing end OR
          the currentstart  is before the existingstart date AND the current end is before existing start date
          Have a boolean value turn to true if there is an overlap.
        *
        */
        ObservableList<Appointments> allAppts = null;
        try {
            allAppts = getAllAppt();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        boolean doesOverlap = false;


        for (Appointments allAppt : allAppts) {
            if (!doesOverlap) {
                LocalDateTime startOfAppt = allAppt.getStart();
                LocalDateTime endOfAppt = allAppt.getEnd();
                int apptIdFromTable = allAppt.getAppointmentId();

                if (apptId != apptIdFromTable && start.isBefore(startOfAppt) && (end.isBefore(startOfAppt) ||
                        end.isEqual(startOfAppt))) {
                    doesOverlap = false;

                } else if (apptId != apptIdFromTable && (start.isAfter(endOfAppt) || start.isEqual(endOfAppt))) {
                    doesOverlap = false;
                } else if (apptId == apptIdFromTable) {
                    doesOverlap = false;

                } else {
                    doesOverlap = true;
                }
            }
        }

        return doesOverlap;

    }

    /**
     * Checks if a proposed new appointment time overlaps with existing appointments in the database.
     * @param start Start time of new appointment.
     * @param end End time of new appointment.
     * @return Returns true if the appointment time overlaps and false if it does not.
     */
    public static boolean checkIfNewApptOverlapsWithOtherAppts(LocalDateTime start, LocalDateTime end) {
        /*query all appts and store appointments into an array. create for loop that check each start and end date.
        if boolean check value is false.
          the currentstart  is after the existing end OR
          the currentstart  is before the existingstart date AND the current end is before existing start date
          Have a boolean value turn to true if there is an overlap.
        *
        */
        ObservableList<Appointments> allAppts = null;
        try {
            allAppts = getAllAppt();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        boolean doesOverlap = false;


        for (Appointments allAppt : allAppts) {

            if (!doesOverlap) {
                LocalDateTime startOfAppt = allAppt.getStart();
                LocalDateTime endOfAppt = allAppt.getEnd();

                if (start.isBefore(startOfAppt) && (end.isBefore(startOfAppt) ||
                        end.isEqual(startOfAppt))) {
                    doesOverlap = false;

                } else if (start.isAfter(endOfAppt) || start.isEqual(endOfAppt)) {
                    doesOverlap = false;
                } else {
                    doesOverlap = true;
                }


            }
        }

        return doesOverlap;

    }

    /**
     * Updates an existing appointment in the Appointments table in the database.
     * @param updatedAppt Updated appointment object containing information from the update appointment form.
     */
    public static void updateAppointment(Appointments updatedAppt) {

        int apptId = updatedAppt.getAppointmentId();
        int customerId = updatedAppt.getCustomerId();
        int userId = updatedAppt.getUserId();
        int contactId = updatedAppt.getContactId();
        String title = updatedAppt.getTitle();
        String description = updatedAppt.getDescription();
        String location = updatedAppt.getLocation();
        String type = updatedAppt.getType();
        LocalDateTime start = updatedAppt.getStart();
        LocalDateTime end = updatedAppt.getEnd();

        Timestamp startTimeStamp = Timestamp.valueOf(start);
        Timestamp endTimeStamp = Timestamp.valueOf(end);

        String sqlStatement = "UPDATE appointments SET " +
                "title = ?" +
                ", description = ?" +
                ", location = ?" +
                ", type = ?" +
                ", start = ?" +
                ", end = ?" +
                ", customer_id = ?" +
                ", contact_Id = ?" +
                ", user_id = ?" +
                " WHERE appointment_Id = ?";

        Connection connection = openConnection();
        try {
            PreparedStatement stmnt = connection.prepareStatement(sqlStatement);
            stmnt.setString(1, title);
            stmnt.setString(2, description);
            stmnt.setString(3, location);
            stmnt.setString(4, type);
            stmnt.setTimestamp(5, startTimeStamp);
            stmnt.setTimestamp(6, endTimeStamp);
            stmnt.setInt(7, customerId);
            stmnt.setInt(8, contactId);
            stmnt.setInt(9, userId);
            stmnt.setInt(10, apptId);

            int updateCount = stmnt.executeUpdate();
            System.out.println(updateCount);
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Adds a new appointment to the Appointments table in the database.
      * @param title title of new appointment.
     * @param description Description of new appointment.
     * @param location location of new appointment.
     * @param type type of new appointment.
     * @param start start time of new appointment.
     * @param end end time of new appointment.
     * @param customerId customer ID of new appointment.
     * @param userId user ID of new appointment.
     * @param contactId contact ID of new appointment.
     */
    public static void addAppointment(String title, String description, String location, String type,
                                      LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {

        Timestamp startTimeStamp = Timestamp.valueOf(start);
        Timestamp endTimeStamp = Timestamp.valueOf(end);

        String sqlStatement = "INSERT INTO appointments " +
                "(Title,description,location,type,start,end,customer_Id,User_Id,Contact_Id)" +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        Connection connection = openConnection();
        try {
            PreparedStatement stmnt = connection.prepareStatement(sqlStatement);
            stmnt.setString(1, title);
            stmnt.setString(2, description);
            stmnt.setString(3, location);
            stmnt.setString(4, type);
            stmnt.setTimestamp(5, startTimeStamp);
            stmnt.setTimestamp(6, endTimeStamp);
            stmnt.setInt(7, customerId);
            stmnt.setInt(8, userId);
            stmnt.setInt(9, contactId);

            stmnt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            closeConnection(connection);
        }

    }

    /**
     * Checks if a customer has an appointment based on customer ID.
     * @param customerId customer ID of selected customer.
     * @return Returns a true value if a customer does have an appointment and false if they do not.
     * @throws SQLException
     */
    public static boolean checkIfCustomerHasApptUsingCustId(int customerId) throws SQLException {

        String sqlStatement = "select appointment_Id from appointments where customer_id =" + customerId + ";";
        ObservableList<Integer> listofAppt = FXCollections.observableArrayList();
        Connection connection = openConnection();

        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int apptId = result.getInt(APPOINTMENT_ID);
                listofAppt.add(apptId);

            }
        } catch (NullPointerException throwable) {
            throwable.printStackTrace();
            closeConnection(connection);
        }

        boolean haveAppt = false;

        try {
            if (!listofAppt.isEmpty()) {
                haveAppt = true;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            closeConnection(connection);
        }

        return haveAppt;
    }

    /**
     * Deletes the selected appointment using appointment ID.
     * @param apptId Appointment ID of existing appointment.
     */
    public static void deleteSelectedApptUsingApptId(int apptId) {

        String sqlStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";

        Connection connection = openConnection();
        try {
            PreparedStatement stmnt = connection.prepareStatement(sqlStatement);
            stmnt.setInt(1, apptId);
            stmnt.executeUpdate(); //prepared statements should not have sqlStatement again.


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    /**
     * Creates a list of appointments with in the next 15 minutes of the user login time.
     * @param loginTime Time the user successfully logs in.
     * @return Returns an observable list of appointments.
     */
    public static ObservableList<Appointments> listApptIn15MinSinceLogIn(LocalDateTime loginTime) {
        ObservableList<Appointments> listApptIn15 = FXCollections.observableArrayList();
        try {
            ObservableList<Appointments> listofAppt = getAllAppt();

            for (Appointments appointments : listofAppt) {
                LocalDateTime apptStartTime = appointments.getStart();
                LocalDateTime loginTimePlus15 = loginTime.plusMinutes(15);


                if (apptStartTime.isEqual(loginTime) || (apptStartTime.isAfter(loginTime) && apptStartTime.isBefore(loginTimePlus15))) {
                    listApptIn15.add(appointments);
                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listApptIn15;
    }

    /**
     * Creates a distinct list of type of appointments form the database.
     * @return Returns an observable list of string.
     */
    public static ObservableList<String> getListOfTypeOfAppts() {

        ObservableList<String> listOfType = FXCollections.observableArrayList();
        String sqlStatement = "select distinct type from appointments;";
        listOfType.add(0, "All Types");

        Connection connection = openConnection();
        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                String type = result.getString(1);
                listOfType.add(type);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return listOfType;

    }

    /**
     * Creates an observable list of months that has appointments as well as the number of appointments with in that month using the TotalApptbyMonth objects.
     * @param type String containing a type of appointment.
     * @return Returns an Observable list of TotalApptbyMonth objects.
     */
    public static ObservableList<TotalApptbyMonth> getTotalApptByMonth(String type) {

        ObservableList<TotalApptbyMonth> listTotalApptByMonth = FXCollections.observableArrayList();
        String appendedSql = " ";


        if (!type.equals("All Types")) {
            appendedSql = "where type = '" + type + "' ";

        }
        String sqlStatement = "select monthname(start) as Month, count(appointment_ID) as Total_NumberOfAppointment from appointments " + appendedSql + "group by month(start) order by month(start);";


        Connection connection = openConnection();
        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                String month = result.getString(1);
                int totalAppt = result.getInt(2);
                TotalApptbyMonth monthTotal = new TotalApptbyMonth(month, totalAppt);
                listTotalApptByMonth.add(monthTotal);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return listTotalApptByMonth;
    }

    /**
     * Gets a list of appointments associated with a selected contact name.
      * @param contactName Selected contact name.
     * @return Returns an observable list of appointments.
     */
    public static ObservableList<Appointments> getApptUsingContactName(String contactName) {
        ObservableList<Appointments> listAppt = FXCollections.observableArrayList();
        String appendedSql = " ";

        if (!contactName.equals("All")) {
            appendedSql = "where contact_name = '" + contactName + "'";

        }
        String sqlStatement = "select contact_Name, Appointment_ID, title, type , description, start,end,customer_Id from appointments a inner join contacts c on a.contact_id = c.Contact_ID " + appendedSql + " ;";


        Connection connection = openConnection();
        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int apptID = result.getInt("Appointment_ID");
                String title = result.getString(TITLE);
                String type = result.getString(TYPE);
                String description = result.getString(DESCRIPTION);
                LocalDateTime start = result.getTimestamp(START).toLocalDateTime();
                LocalDateTime end = result.getTimestamp(END).toLocalDateTime();
                int customerId = result.getInt(CUSTOMER_ID);

                Appointments appt = new Appointments(apptID, title, description, "none", type, start, end, customerId, 1, 1);
                listAppt.add(appt);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return listAppt;
    }

    /**
     * Gets a list of appointments associated with a selected username.
     * @param username Selected username.
     * @return Returns an observable list of appointments.
     */
    public static ObservableList<Appointments> getApptUsingUsername(String username) {
        ObservableList<Appointments> allAssociatedAppt = FXCollections.observableArrayList();


        String sqlStatement = "select Appointment_ID, title, type , description, start,end,customer_Id,Location from " +
                "appointments a inner join users u on a.User_ID = u.User_ID where User_name = '" + username + "' order by a.start";


        Connection connection = openConnection();
        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int apptID = result.getInt("Appointment_ID");
                String title = result.getString(TITLE);
                String type = result.getString(TYPE);
                String description = result.getString(DESCRIPTION);
                LocalDateTime start = result.getTimestamp(START).toLocalDateTime();
                LocalDateTime end = result.getTimestamp(END).toLocalDateTime();
                int customerId = result.getInt(CUSTOMER_ID);

                Appointments appt = new Appointments(apptID, title, description, "none", type, start, end, customerId, 1, 1);
                allAssociatedAppt.add(appt);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return allAssociatedAppt;


    }

}
