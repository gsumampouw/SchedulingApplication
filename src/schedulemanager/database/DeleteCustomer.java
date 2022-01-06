package schedulemanager.database;

import java.sql.SQLException;

public interface DeleteCustomer {

     void removeCustomer(int customerId) throws SQLException;
}
