package li.clientmgt.view;

import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import li.clientmgt.model.Student;

/**
 * The controller for the student score statistics view.
 */
public class ScoreStatisticsController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> quarterNames = FXCollections.observableArrayList();
    private Student student;
    
    /**
     * Initialize the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Get an array with the quarter names.
    	String[] quarters = {"1st Quarter", "2nd Quarter", "3rd Quarter", "4th Quarter"};
    	
        // Convert it to a list and add it to our ObservableList of quarters.
        quarterNames.addAll(Arrays.asList(quarters));

        // Assign the quarter names as categories for the horizontal axis.
        xAxis.setCategories(quarterNames);
    }

    /**
     * Set the student scores to show the statistics for.
     * 
     * @param student
     */
    public void setStudentData(Student student) {
    	this.student = student;
    	
        int[] scores = new int[4];
        scores[0] = student.getFirstScore();
        scores[1] = student.getSecondScore();
        scores[2] = student.getThirdScore();
        scores[3] = student.getForthScore();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each quarter. Add it to the series.
        for (int i = 0; i < scores.length; i++) {
            series.getData().add(new XYChart.Data<>(quarterNames.get(i), scores[i]));
        }

        barChart.getData().add(series);
    }
}