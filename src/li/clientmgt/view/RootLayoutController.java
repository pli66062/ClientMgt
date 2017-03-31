package li.clientmgt.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import li.clientmgt.MainApp;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Clear management overview.
     */
    @FXML
    private void handleClear() {
        mainApp.getStudentData().clear();
        mainApp.getLessonData().clear();
    }

    /**
     * Create an empty student overview.
     */
    @FXML
    private void handleNewStudent() {
        mainApp.getStudentData().clear();
        mainApp.setStudentFilePath(null);
    }
    
    /**
     * Create an empty lesson overview.
     */
    @FXML
    private void handleNewLesson() {
        mainApp.getLessonData().clear();
        mainApp.setLessonFilePath(null);
    }

    /**
     * Open a FileChooser to let the user select a student file to load.
     */
    @FXML
    private void handleOpenStudent() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadStudentDataFromFile(file);
        }
    }
    
    /**
     * Open a FileChooser to let the user select a lesson file to load.
     */
    @FXML
    private void handleOpenLesson() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadLessonDataFromFile(file);
        }
    }

    /**
     * Save the file to the student file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSaveStudent() {
        File studentFile = mainApp.getStudentFilePath();
        if (studentFile != null) {
            mainApp.saveStudentDataToFile(studentFile);
        } else {
            handleSaveAsStudent();
        }
    }
    
    /**
     * Save the file to the lesson file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSaveLesson() {
        File lessonFile = mainApp.getLessonFilePath();
        if (lessonFile != null) {
            mainApp.saveLessonDataToFile(lessonFile);
        } else {
            handleSaveAsLesson();
        }
    }

    /**
     * Open a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAsStudent() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveStudentDataToFile(file);
        }
    }
    
    /**
     * Open a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAsLesson() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveLessonDataToFile(file);
        }
    }

    /**
     * Open an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Client Management");
    	alert.setHeaderText("About");
    	alert.setContentText("Client Management v1.0\nAuthor: Patrick Li\nWebsite: http://www.jchs.net");

    	alert.showAndWait();
    }

    /**
     * Close the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}