package schedulemanager.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedulemanager.database.AppointmentDao;
import schedulemanager.domain.Appointments;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Display {
    public Display() {
    }
    /**
     * Creates a list of appointments with in the next 15 minutes of the user login time.
     *
     * @param loginTime Time the user successfully logs in.
     * @return Returns an observable list of appointments.
     */
    // TODO In general, business logic should not be in DAO or Controller class, but in Service package classes
    public ObservableList<Appointments> listApptIn15MinSinceLogIn(LocalDateTime loginTime) {
        ObservableList<Appointments> listApptIn15 = FXCollections.observableArrayList();
        try {
            AppointmentDao appointmentDao = new AppointmentDao();
            ObservableList<Appointments> listofAppt = appointmentDao.getAllAppt();

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
     * Creates an observable list of time starting from a local time of 00:00 to 23:00 in 1 hour increments
     *
     * @return Returns an observable list of time.
     */
    public static final ObservableList<LocalTime> listOfTime() {

        // TODO: Make this list a global static (maybe final as well if possible) that only gets initialized once
        // TODO: Move the global variable and this method to a more general location, such as a Util class
        ObservableList<LocalTime> localTimes = FXCollections.observableArrayList();
        LocalTime time = LocalTime.of(0, 0);

        for (int i = 0; i < 24; i++) {
            localTimes.add(time);
            time = time.plusHours(1);
        }

        return localTimes;
    }
}
