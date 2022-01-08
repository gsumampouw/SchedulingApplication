package main.schedulemanager.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.schedulemanager.database.AppointmentDao;
import main.schedulemanager.domain.Appointments;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;

import static main.schedulemanager.database.JDBC.closeConnection;
import static main.schedulemanager.database.JDBC.openConnection;

public class Checks {

    public Checks() {
    }

    /**
     * Checks if start and end time is with in EST business hours of 8AM to 10PM.  Appointments must be in the same day to be true.
     *
     * @param start Start time of appointment.
     * @param end   End time of appointment.
     * @return Returns a boolean value of  true if appointment is in office hours and false if it is not.
     */
    // TODO In general, business logic should not be in DAO or Controller class, but in Service package classes
    // TODO: Read about why to avoid using Static most of the time
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
     *
     * @param start  Start time of appointment.
     * @param end    End time of appointment.
     * @param apptId Appointment ID of selected appointment.
     * @return Returns true if the start or end time of the appointment overlaps another appointment, and returns false if it does not.
     */
    // TODO In general, business logic should not be in DAO or Controller class, but in Service package classes
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
            AppointmentDao appointmentDao = new AppointmentDao();
            allAppts = appointmentDao.getAllAppt();
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
     *
     * @param start Start time of new appointment.
     * @param end   End time of new appointment.
     * @return Returns true if the appointment time overlaps and false if it does not.
     */
    // TODO In general, business logic should not be in DAO or Controller class, but in Service package classes
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
            AppointmentDao appointmentDao = new AppointmentDao();
            allAppts =  appointmentDao.getAllAppt();
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
     * Checks if a customer has an appointment based on customer ID.
     *
     * @param customerId customer ID of selected customer.
     * @return Returns a true value if a customer does have an appointment and false if they do not.
     * @throws SQLException
     */
    // TODO In general, business logic should not be in DAO or Controller class, but in Service package classes
    public static boolean checkIfCustomerHasApptUsingCustId(int customerId) throws SQLException {

        String sqlStatement = "select appointment_Id from appointments where customer_id =" + customerId + ";";
        ObservableList<Integer> listofAppt = FXCollections.observableArrayList();
        Connection connection = openConnection();

        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int apptId = result.getInt("APPOINTMENT_ID");
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

}
