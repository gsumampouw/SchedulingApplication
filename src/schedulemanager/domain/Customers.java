package schedulemanager.domain;

/**
 * This class creates Customer objects.
 */
public class Customers {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionId;

    /**
     * This is the constructor for the Customer class.
     * @param customerId - Sets customerId.
     * @param customerName - Sets customerName.
     * @param address - Sets address.
     * @param postalCode - Sets postalCode.
     * @param phone - Sets phone.
     * @param divisionId - Sets divisionId.
     */
    public Customers(int customerId, String customerName, String address, String postalCode, String phone, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }

    /**
     *
     * @return The customerId.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId - Sets customerId.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return The customerName.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param customerName - Sets the customerName.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * @return The address.
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address - Sets address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return The postalCode.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode - Sets the postalCode.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return The phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone - Sets phone.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return The divisionId.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     *
     * @param divisionId - Sets divisionId.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
}
