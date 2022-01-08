package main.schedulemanager.domain;

/**
 *This class creates Countries objects.
 */
public class Countries {

    private int countryId;
    private String country;

    /**
     * This is a constructor for the Coutnries class.
     * @param countryId - Sets countryId.
     * @param country - Sets country.
     */
    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
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
     * @param countryId - Sets countryID.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     *
     * @return The country.
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country - Sets the country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Overides the way Country class object is displayed to only show country name.
     * @return The country.
     */
    @Override
    public String toString(){
        return(country);
    }
}
