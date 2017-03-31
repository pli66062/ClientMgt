 package li.clientmgt.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import li.clientmgt.model.Student;

/**
 * View details of a student.
 */
public class StudentDetailsController { 
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label zipCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label gradeLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label hourlyRateLabel;
    @FXML
    private Label amountOweLabel;
    
    private Student student;

    /**
     * Initialize the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Set the student to be viewed.
     * 
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
     
        firstNameLabel.setText(student.getFirstName());
        lastNameLabel.setText(student.getLastName());
        streetLabel.setText(student.getStreet());
        zipCodeLabel.setText(Integer.toString(student.getZipCode()));
        cityLabel.setText(student.getCity());
        gradeLabel.setText(Integer.toString(student.getGrade()));
        phoneLabel.setText(student.getPhone());
        emailLabel.setText(student.getEmail());
        hourlyRateLabel.setText(Integer.toString(student.getHourlyRate()));
        amountOweLabel.setText(Integer.toString(student.getAmountOwe()));        
    }
}