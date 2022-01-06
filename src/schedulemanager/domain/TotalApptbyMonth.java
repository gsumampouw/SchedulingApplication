package schedulemanager.domain;

public class TotalApptbyMonth {

    private String month;
    private int total;

    public TotalApptbyMonth(String month, int total) {
        this.month = month;
        this.total = total;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
