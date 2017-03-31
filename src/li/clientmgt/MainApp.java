package li.clientmgt;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import li.clientmgt.model.Lesson;
import li.clientmgt.model.LessonListWrapper;
import li.clientmgt.model.Student;
import li.clientmgt.model.StudentListWrapper;
import li.clientmgt.view.StudentEditDialogController;
import li.clientmgt.view.LessonEditDialogController;
import li.clientmgt.view.ManagementOverviewController;
import li.clientmgt.view.StudentDetailsController;
import li.clientmgt.view.RootLayoutController;
import li.clientmgt.view.ScoreStatisticsController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    /**
     * The data as an observable list of Students and Lessons.
     */
    private ObservableList<Student> studentData = FXCollections.observableArrayList();
    private ObservableList<Lesson> lessonData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add 1st time student data
        studentData.add(new Student("Richard", "Lee"));
        studentData.add(new Student("Pat", "Muller"));
        studentData.add(new Student("Tom", "Kurz"));
        studentData.add(new Student("June", "Shoemaker"));
        studentData.add(new Student("Tony", "Mai"));
        // Add 1st time lesson data
        lessonData.add(new Lesson("Monday", "09:30")); 
        lessonData.add(new Lesson("Tuesday", "12:30"));
        lessonData.add(new Lesson("Tuesday", "15:00"));
    }

    /**
     * Return the data as an observable list of Students. 
     * @return
     */
    public ObservableList<Student> getStudentData() {
        return studentData;
    }
    
    /**
     * Return the data as an observable list of lessons. 
     * @return
     */
    public ObservableList<Lesson> getLessonData() {
        return lessonData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Client Management");

        initRootLayout();

        showManagementOverview();
    }

    /**
     * Initialize the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened student file.
        File studentFile = getStudentFilePath();
        if (studentFile != null) {
            loadStudentDataFromFile(studentFile);
        }
        // Try to load last opened lesson file.
        File lessonFile = getLessonFilePath();
        if (lessonFile != null) {
            loadLessonDataFromFile(lessonFile);
        }
    }

    /**
     * Show the management overview inside the root layout.
     */
    public void showManagementOverview() {
        try {
            // Load management overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ManagementOverview.fxml"));
            AnchorPane ManagementOverview = (AnchorPane) loader.load();

            // Set management overview into the center of root layout.
            rootLayout.setCenter(ManagementOverview);

            // Give the controller access to the main app.
            ManagementOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Open a dialog to edit details for the specified lesson. If the user
     * clicks OK, the changes are saved into the provided lesson object and true
     * is returned.
     * 
     * @param lesson the lesson object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showLessonEditDialog(Lesson lesson) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LessonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Lesson");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the lesson into the controller.
            LessonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLesson(lesson);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Open a dialog to edit details for the specified student. If the user
     * clicks OK, the changes are saved into the provided student object and true
     * is returned.
     * 
     * @param student the student object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showStudentEditDialog(Student student) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/StudentEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the student into the controller.
            StudentEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudent(student);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Open a dialog to view details for the specified student.
     * 
     * @param student the student object to be viewed
     */
    public void showStudentDetails(Student student) {
          try {
              // Load the fxml file and create a new stage for the popup dialog.
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(MainApp.class.getResource("view/StudentDetails.fxml"));
              AnchorPane page = (AnchorPane) loader.load();

              // Create the dialog Stage.
              Stage dialogStage = new Stage();
              dialogStage.setTitle("Student Details");
              dialogStage.initModality(Modality.WINDOW_MODAL);
              dialogStage.initOwner(primaryStage);
              Scene scene = new Scene(page);
              dialogStage.setScene(scene);

              // Set the student into the controller.
              StudentDetailsController controller = loader.getController();
              controller.setStudent(student);

              // Show the dialog and wait until the user closes it
              dialogStage.show();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
    
    /**
     * Open a dialog to show student score statistics.
     *     
     * @param student the student object to show scores
     */
    public void showScoreStatistics(Student student) {
        try {
            // Load the fxml file and create a new stage for the pop up.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ScoreStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Score Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            ScoreStatisticsController controller = loader.getController();
            controller.setStudentData(student);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Return the student file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getStudentFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String studentFilePath = prefs.get("studentFilePath", null);
        if (studentFilePath != null) {
            return new File(studentFilePath);
        } else {
            return null;
        }
    }
    
    /**
     * Returns the lesson file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getLessonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String lessonFilePath = prefs.get("lessonFilePath", null);
        if (lessonFilePath != null) {
            return new File(lessonFilePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the student file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setStudentFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("studentFilePath", file.getPath());
        } else {
            prefs.remove("studentFilePath");
        }
    }
    
    /**
     * Sets the lesson file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setLessonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("lessonFilePath", file.getPath());
        } else {
            prefs.remove("lessonFilePath");
        }
    }
    
    /**
     * Load student data from the specified file. The current student data will
     * be replaced.
     * 
     * @param file
     */
    public void loadStudentDataFromFile(File file) {
        try {
            JAXBContext studentContext = JAXBContext.newInstance(StudentListWrapper.class);
            Unmarshaller studentUnmarshaller = studentContext.createUnmarshaller();

            // Read XML from the file and unmarshall
            StudentListWrapper studentWrapper = (StudentListWrapper) studentUnmarshaller.unmarshal(file);

            studentData.clear();
            studentData.addAll(studentWrapper.getStudents());

            // Save the file path to the registry.
        	System.out.println(file.getPath());
            setStudentFilePath(file);

        } catch (Exception e) { // catch exception.
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not load data");
        	alert.setContentText("Could not load data from file:\n" + file.getPath());
        	
        	alert.showAndWait();
        }
    }
    /**
     * Load lesson data from the specified file. The current lesson data will
     * be replaced.
     * 
     * @param file
     */
    public void loadLessonDataFromFile(File file) {
        try {            
            JAXBContext lessonContext = JAXBContext.newInstance(LessonListWrapper.class);
            Unmarshaller lessonUnmarshaller = lessonContext.createUnmarshaller();

            // Read XML from the file and unmarshall
            LessonListWrapper lessonWrapper = (LessonListWrapper) lessonUnmarshaller.unmarshal(file);

            lessonData.clear();
            lessonData.addAll(lessonWrapper.getLessons());

            // Save the file path to the registry.
            setLessonFilePath(file);

        } catch (Exception e) { // catch exception.
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not load data");
        	alert.setContentText("Could not load data from file:\n" + file.getPath());
        	
        	alert.showAndWait();
        }
    }

    /**
     * Save the current student data to the specified file.
     * 
     * @param file
     */
    public void saveStudentDataToFile(File file) {
        try {
            JAXBContext studentContext = JAXBContext.newInstance(StudentListWrapper.class);
            Marshaller studentMarshaller = studentContext.createMarshaller();
            studentMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrap student data.
            StudentListWrapper studentWrapper = new StudentListWrapper();
            studentWrapper.setStudents(studentData);
            
            // Marshall and save XML to the file.
            studentMarshaller.marshal(studentWrapper, file);
            // Save the file path to the registry.
            setStudentFilePath(file);
        } catch (Exception e) { // catch exception
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not save data");
        	alert.setContentText("Could not save data to file:\n" + file.getPath());
        	
        	alert.showAndWait();
        }
    }
    
    /**
     * Save the current lesson data to the specified file.
     * 
     * @param file
     */
    public void saveLessonDataToFile(File file) {
        try {         
            JAXBContext lessonContext = JAXBContext.newInstance(LessonListWrapper.class);
            Marshaller lessonMarshaller = lessonContext.createMarshaller();
            lessonMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrap lesson data.
            LessonListWrapper lessonWrapper = new LessonListWrapper();
            lessonWrapper.setLessons(lessonData);

            // Marshall and save XML to the file.
            lessonMarshaller.marshal(lessonWrapper, file);

            // Save the file path to the registry.
            setLessonFilePath(file);
        } catch (Exception e) { // catch exception
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not save data");
        	alert.setContentText("Could not save data to file:\n" + file.getPath());
        	
        	alert.showAndWait();
        }
    }
   
    /**
     * Return the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}