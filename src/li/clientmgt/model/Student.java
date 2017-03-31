package li.clientmgt.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a student.
 */
public class Student {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final IntegerProperty zipCode;
    private final StringProperty city;
    private final IntegerProperty grade;
    private final StringProperty phone;
    private final StringProperty email;
    private final IntegerProperty hourlyRate;
    private final IntegerProperty amountOwe;
    private final IntegerProperty firstScore;
    private final IntegerProperty secondScore;
    private final IntegerProperty thirdScore;
    private final IntegerProperty forthScore;

    /**
     * Default constructor.
     */
    public Student() {
        this(null, null);
    }

    /**
     * Constructor with initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Student(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        //Initial data.
        this.street = new SimpleStringProperty("");
        this.zipCode = new SimpleIntegerProperty(0);
        this.city = new SimpleStringProperty("");
        this.grade = new SimpleIntegerProperty(0);
        this.phone = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.hourlyRate = new SimpleIntegerProperty(0);
        this.amountOwe = new SimpleIntegerProperty(0);
        this.firstScore = new SimpleIntegerProperty(0);
        this.secondScore = new SimpleIntegerProperty(0);
        this.thirdScore = new SimpleIntegerProperty(0);
        this.forthScore = new SimpleIntegerProperty(0);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty streetProperty() {
        return street;
    }

    public int getZipCode() {
        return zipCode.get();
    }

    public void setZipCode(int zipCode) {
        this.zipCode.set(zipCode);
    }

    public IntegerProperty zipCodeProperty() {
        return zipCode;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }
    
    public int getGrade() {
        return grade.get();
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }

    public IntegerProperty gradeProperty() {
        return grade;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty phoneProperty() {
        return phone;
    }
    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }
    
    public int getHourlyRate() {
        return hourlyRate.get();
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate.set(hourlyRate);
    }

    public IntegerProperty hourlyRateProperty() {
        return hourlyRate;
    }
    
    public int getAmountOwe() {
        return amountOwe.get();
    }

    public void setAmountOwe(int amountOwe) {
        this.amountOwe.set(amountOwe);
    }

    public IntegerProperty amountOweProperty() {
        return amountOwe;
    }
    
    public int getFirstScore() {
        return firstScore.get();
    }

    public void setFirstScore(int firstScore) {
        this.firstScore.set(firstScore);
    }
    
    public IntegerProperty firstScoreProperty() {
        return firstScore;
    }
    
    public int getSecondScore() {
        return secondScore.get();
    }

    public void setSecondScore(int secondScore) {
        this.secondScore.set(secondScore);
    }

    public IntegerProperty secondScoreProperty() {
        return secondScore;
    }
    
    public int getThirdScore() {
        return thirdScore.get();
    }

    public void setThirdScore(int thirdScore) {
        this.thirdScore.set(thirdScore);
    }
    
    public IntegerProperty thirdScoreProperty() {
        return thirdScore;
    }
    
    public int getForthScore() {
        return forthScore.get();
    }

    public void setForthScore(int forthScore) {
        this.forthScore.set(forthScore);
    }
    
    public IntegerProperty forthScoreProperty() {
        return forthScore;
    }
}