package schedulemanager.domain;

/**
 * This class creates Divisions objects.
 */
public class Divisions {

    private int divisionId;
    private String division;
    private int countryId;

    /**
     * This is the contructor for the Divisions class.
     * @param divisionId - Sets divisionId.
     * @param division - Sets divisions.
     * @param countryId - Sets countryId.
     */
    public Divisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
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
     * @param divisionId - Sets the divisionsId.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     *
     * @return The division.
     */
    public String getDivision() {
        return division;
    }

    /**
     *
     * @param division - Sets the division.
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     *
     * @return The countryId.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     *
     * @param countryId - Sets countryId.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Overrides the way division objects are displayed to show only division name.
     * @return The division.
     */
    @Override
    public String toString(){
        return(division);
    }

}
