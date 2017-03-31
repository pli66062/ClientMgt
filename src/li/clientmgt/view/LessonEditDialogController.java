package li.clientmgt.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import li.clientmgt.model.Lesson;

/**
 * Dialog to edit details of a lesson.
 */
public class LessonEditDialogController {

    @FXML
    private TextField studentNameField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField lessonDateField;
    @FXML
    private TextField lessonTimeField;

    private Stage dialogStage;
    private Lesson lesson;
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
     * Set the lesson to be edited in the dialog.
     * 
     * @param lesson
     */
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;

        lessonDateField.setText(lesson.getLessonDate());
        lessonTimeField.setText(lesson.getLessonTime());
        durationField.setText(Integer.toString(lesson.getDuration()));
        subjectField.setText(lesson.getSubject());
        studentNameField.setText(lesson.getStudentName());
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
            lesson.setLessonDate(lessonDateField.getText());
            lesson.setLessonTime(lessonTimeField.getText());
            lesson.setDuration(Integer.parseInt(durationField.getText()));
            lesson.setSubject(subjectField.getText());
            lesson.setStudentName(studentNameField.getText());
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

        if (lessonDateField.getText() == null || lessonDateField.getText().length() == 0) {
            errorMessage += "No valid lesson Date!\n"; 
        }
        
        if (lessonTimeField.getText() == null || lessonTimeField.getText().length() == 0) {
            errorMessage += "No valid lesson Time!\n"; 
        }

        if (durationField.getText() == null || durationField.getText().length() == 0) {
            errorMessage += "No valid duration!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(durationField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid duration (must be an integer)!\n"; 
            }
        }

        if (subjectField.getText() == null || subjectField.getText().length() == 0) {
            errorMessage += "No valid subject!\n"; 
        }
        
        if (studentNameField.getText() == null || studentNameField.getText().length() == 0) {
            errorMessage += "No valid student name!\n"; 
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