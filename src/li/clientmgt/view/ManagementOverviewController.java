package li.clientmgt.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import li.clientmgt.MainApp;
import li.clientmgt.model.Lesson;
import li.clientmgt.model.Student;

public class ManagementOverviewController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
   
    @FXML
    private TableView<Lesson> lessonTable;
    @FXML
    private TableColumn<Lesson, String> lessonDateColumn;
    @FXML
    private TableColumn<Lesson, String> lessonTimeColumn;
    @FXML
    private TableColumn<Lesson, Integer> durationColumn;
    @FXML
    private TableColumn<Lesson, String> subjectColumn;
    @FXML
    private TableColumn<Lesson, String> studentNameColumn;
    

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ManagementOverviewController() {
    }

    /**
     * Initialize the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the student table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        // Initialize the lesson table with the Five columns.
        lessonDateColumn.setCellValueFactory(cellData -> cellData.getValue().lessonDateProperty());
        lessonTimeColumn.setCellValueFactory(cellData -> cellData.getValue().lessonTimeProperty());
		durationColumn.setCellValueFactory(cellData-> cellData.getValue().durationProperty().asObject());			
		subjectColumn.setCellValueFactory(cellData-> cellData.getValue().subjectProperty());
        studentNameColumn.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
    }

    /**
     * Called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        studentTable.setItems(mainApp.getStudentData());
        lessonTable.setItems(mainApp.getLessonData());
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteStudent() {
        int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            studentTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Student Selected");
            alert.setContentText("Please select a Student in the table.");
            
            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new Student.
     */
    @FXML
    private void handleNewStudent() {
        Student tempStudent = new Student();
        boolean okClicked = mainApp.showStudentEditDialog(tempStudent);
        if (okClicked) {
            mainApp.getStudentData().add(tempStudent);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected Student.
     */
    @FXML
    private void handleEditStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
        	mainApp.showStudentEditDialog(selectedStudent);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Student Selected");
            alert.setContentText("Please select a Student in the table.");
            
            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the Student Details button. Opens an overview
     * for the selected student.
     */
    @FXML
    private void handleStudentDetails() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
        	mainApp.showStudentDetails(selectedStudent);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Student Selected");
            alert.setContentText("Please select a student in the table.");
            
            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new lesson.
     */
    @FXML
    private void handleNewLesson() {
        Lesson tempLesson = new Lesson();
        boolean okClicked = mainApp.showLessonEditDialog(tempLesson);
        if (okClicked) {
            mainApp.getLessonData().add(tempLesson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected lesson.
     */
    @FXML
    private void handleEditLesson() {
        Lesson selectedLesson = lessonTable.getSelectionModel().getSelectedItem();
        if (selectedLesson != null) {
        	mainApp.showLessonEditDialog(selectedLesson);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Lesson Selected");
            alert.setContentText("Please select a lesson in the table.");
            
            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteLesson() {
        int selectedIndex = lessonTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            lessonTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Lesson Selected");
            alert.setContentText("Please select a lesson in the table.");
            
            alert.showAndWait();
        }
    }
    
    /**
     * Open the student score statistics.
     */
    @FXML
    private void handleShowScoreStatistics() {
      Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
      if (selectedStudent != null) {
    	  mainApp.showScoreStatistics(selectedStudent);
      } else {
          // Nothing selected.
          Alert alert = new Alert(AlertType.WARNING);
          alert.initOwner(mainApp.getPrimaryStage());
          alert.setTitle("No Selection");
          alert.setHeaderText("No Student Selected");
          alert.setContentText("Please select a Student in the table.");
          
          alert.showAndWait();
      }
    }
}