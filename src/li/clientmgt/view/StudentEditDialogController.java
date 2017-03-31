package li.clientmgt.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import li.clientmgt.model.Student;

/**
 * Dialog to edit details of a student.
 */
public class StudentEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField gradeField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField hourlyRateField;
    @FXML
    private TextField amountOweField;
    @FXML
    private TextField firstScoreField;
    @FXML
    private TextField secondScoreField;
    @FXML
    private TextField thirdScoreField;
    @FXML
    private TextField forthScoreField;

    private Stage dialogStage;
    private Student student;
    private boolean okClicked = false;

    /**
     * Initialize the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Set the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Set the student to be edited in the dialog.
     * 
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;

        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        streetField.setText(student.getStreet());
        zipCodeField.setText(Integer.toString(student.getZipCode()));
        cityField.setText(student.getCity());
        gradeField.setText(Integer.toString(student.getGrade()));
        phoneField.setText(student.getPhone());
        emailField.setText(student.getEmail());
        hourlyRateField.setText(Integer.toString(student.getHourlyRate()));
        amountOweField.setText(Integer.toString(student.getAmountOwe()));
        firstScoreField.setText(Integer.toString(student.getFirstScore()));
        secondScoreField.setText(Integer.toString(student.getSecondScore()));
        thirdScoreField.setText(Integer.toString(student.getThirdScore()));
        forthScoreField.setText(Integer.toString(student.getForthScore()));
    }

    /**
     * Return true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            student.setFirstName(firstNameField.getText());
            student.setLastName(lastNameField.getText());
            student.setStreet(streetField.getText());
            student.setZipCode(Integer.parseInt(zipCodeField.getText()));
            student.setCity(cityField.getText());
            student.setGrade(Integer.parseInt(gradeField.getText()));
            student.setPhone(phoneField.getText());
            student.setEmail(emailField.getText());
            student.setHourlyRate(Integer.parseInt(hourlyRateField.getText()));
            student.setAmountOwe(Integer.parseInt(amountOweField.getText()));
            student.setFirstScore(Integer.parseInt(firstScoreField.getText()));
            student.setSecondScore(Integer.parseInt(secondScoreField.getText()));
            student.setThirdScore(Integer.parseInt(thirdScoreField.getText()));
            student.setForthScore(Integer.parseInt(forthScoreField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validate the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (zipCodeField.getText() == null || zipCodeField.getText().length() == 0) {
            errorMessage += "No valid zip code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(zipCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid zip code (must be an integer)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }
        
        if (gradeField.getText() == null || gradeField.getText().length() == 0) {
            errorMessage += "No valid grade!\n"; 
        } else {
            // try to parse the grade into an int.
            try {
                Integer.parseInt(gradeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid grade (must be an integer)!\n"; 
            }
        }

        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += "No valid phone number!\n"; 
        }

        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid email address!\n"; 
        }
        
        if (hourlyRateField.getText() == null || hourlyRateField.getText().length() == 0) {
            errorMessage += "No valid hourly rate!\n"; 
        } else {
            // try to parse the hourly rate into an int.
            try {
                Integer.parseInt(hourlyRateField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid hourly rate (must be an integer)!\n"; 
            }
        }
        
        if (amountOweField.getText() == null || amountOweField.getText().length() == 0) {
            errorMessage += "No valid amount owe!\n"; 
        } else {
            // try to parse the amount owe into an int.
            try {
                Integer.parseInt(amountOweField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid amount owe (must be an integer)!\n"; 
            }
        }
        
        if (firstScoreField.getText() == null || firstScoreField.getText().length() == 0) {
            errorMessage += "No valid 1st Q score!\n"; 
        } else {
            // try to parse the score into an int.
            try {
                Integer.parseInt(firstScoreField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid 1st Q score (must be an integer)!\n"; 
            }
        }
        
        if (secondScoreField.getText() == null || secondScoreField.getText().length() == 0) {
            errorMessage += "No valid 2nd Q score!\n"; 
        } else {
            // try to parse the score into an int.
            try {
                Integer.parseInt(secondScoreField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid 2nd Q score (must be an integer)!\n"; 
            }
        }
        
        if (thirdScoreField.getText() == null || thirdScoreField.getText().length() == 0) {
            errorMessage += "No valid 3rd Q score!\n"; 
        } else {
            // try to parse the score into an int.
            try {
                Integer.parseInt(thirdScoreField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid 3rd Q score (must be an integer)!\n"; 
            }
        }
        
        if (forthScoreField.getText() == null || forthScoreField.getText().length() == 0) {
            errorMessage += "No valid 4th Q score!\n"; 
        } else {
            // try to parse the score into an int.
            try {
                Integer.parseInt(forthScoreField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid 4th Q score (must be an integer)!\n"; 
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}