package test.schedulemanager.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.schedulemanager.database.AppointmentDao;
import main.schedulemanager.domain.Appointments;
import main.schedulemanager.services.Display;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class DisplayTest {
    @Mock
    AppointmentDao appointmentDao;
    @InjectMocks
    Display display;

    @Test
    public void givenUserHasAppointmentIn15Min_whenLogin_thenEqualsOne() throws Exception {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();
        LocalDateTime loginTime = LocalDateTime.of(2022, 1, 1, 1, 0);
        LocalDateTime apptTime = LocalDateTime.of(2022, 1, 1, 1, 10);
        Appointments appt1 = new Appointments(1, "a", "a", "a", "a", apptTime, apptTime, 1, 1, 1);
        appointments.add(appt1);
        Mockito.when(appointmentDao.getAllAppt()).thenReturn(appointments);
        ObservableList<Appointments> returnedAppt = display.listApptIn15MinSinceLogIn(loginTime);

        Mockito.verify(appointmentDao).getAllAppt();
        Assert.assertEquals(1, returnedAppt.size());

    }

    @Test
    public void givenUserHasAppointmentIn20Min_whenLogin_thenEqualsZero() throws Exception {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();
        LocalDateTime loginTime = LocalDateTime.of(2022, 1, 1, 1, 0);
        LocalDateTime apptTime = LocalDateTime.of(2022, 1, 1, 1, 20);
        Appointments appt1 = new Appointments(1, "a", "a", "a", "a", apptTime, apptTime, 1, 1, 1);
        appointments.add(appt1);
        Mockito.when(appointmentDao.getAllAppt()).thenReturn(appointments);
        ObservableList<Appointments> returnedAppt = display.listApptIn15MinSinceLogIn(loginTime);

        Mockito.verify(appointmentDao).getAllAppt();
        Assert.assertEquals(0, returnedAppt.size());

    }
}
