package test.schedulemanager.services;

import main.schedulemanager.services.Checks;
import org.junit.Assert;
import org.junit.Test;

import java.time.*;

public class ChecksTest {

    @Test
    public void givenStartTimeBeforeOfficeHrs_whenCheckIfTimeIsInOfficeHrsIsCalled_thenFalse(){
        LocalDate date = LocalDate.of(2022,1,1);
        LocalTime start = LocalTime.of(6,0);
        LocalTime end = LocalTime.of(10,0);

        LocalDateTime startTime = LocalDateTime.of(date,start);
        LocalDateTime endTime = LocalDateTime.of(date,end);

        Checks check = new Checks();
        boolean result = check.checkIfTimeIsInOfficeHrs(startTime,endTime);
        Assert.assertFalse(result);
    }

    @Test
    public void givenStartTimeAfterOfficeHrs_whenCheckIfTimeIsInOfficeHrsIsCalled_thenFalse(){
        LocalDate date = LocalDate.of(2022,1,1);
        LocalTime start = LocalTime.of(22,0);
        LocalTime end = LocalTime.of(23,0);

        LocalDateTime startTime = LocalDateTime.of(date,start);
        LocalDateTime endTime = LocalDateTime.of(date,end);

        Checks check = new Checks();
        boolean result = check.checkIfTimeIsInOfficeHrs(startTime,endTime);
        Assert.assertFalse(result);
    }

    @Test
    public void givenEndTimeAfterOfficeHrs_whenCheckIfTimeIsInOfficeHrsIsCalled_thenFalse(){
        LocalDate date = LocalDate.of(2022,1,1);
        LocalTime start = LocalTime.of(12,0);
        LocalTime end = LocalTime.of(23,0);

        LocalDateTime startTime = LocalDateTime.of(date,start);
        LocalDateTime endTime = LocalDateTime.of(date,end);

        Checks check = new Checks();
        boolean result = check.checkIfTimeIsInOfficeHrs(startTime,endTime);
        Assert.assertFalse(result);
    }

    @Test
    public void givenStartAndEndTimeInOfficeHrs_whenCheckIfTimeIsInOfficeHrsIsCalled_thenTrue(){
        LocalDate date = LocalDate.of(2022,1,1);
        LocalTime start = LocalTime.of(11,0);
        LocalTime end = LocalTime.of(12,0);

        LocalDateTime startTime = LocalDateTime.of(date,start);
        LocalDateTime endTime = LocalDateTime.of(date,end);

        Checks check = new Checks();
        boolean result = check.checkIfTimeIsInOfficeHrs(startTime,endTime);
        Assert.assertTrue(result);
    }


}
