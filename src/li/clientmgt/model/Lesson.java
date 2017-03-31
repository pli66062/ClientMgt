package li.clientmgt.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a lesson.
 */
public class Lesson {
	
    private final StringProperty lessonDate;
    private final StringProperty lessonTime;
    private final IntegerProperty duration;    
    private final StringProperty studentName;
    private final StringProperty subject;
    

    /**
     * Default constructor.
     */
    public Lesson() {
        this(null, null);
    }

    /**
     * Constructor with initial data.
     * 
     * @param lessonDate
     * @param lessonTime
     */
    public Lesson(String lessonDate, String lessonTime) {
        this.lessonDate = new SimpleStringProperty(lessonDate);
        this.lessonTime = new SimpleStringProperty(lessonTime);
        this.duration = new SimpleIntegerProperty(0);
        this.subject = new SimpleStringProperty("");
        this.studentName = new SimpleStringProperty("");
    }

    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public String getSubject() {
        return subject.get();
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public String getLessonDate() {
        return lessonDate.get();
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate.set(lessonDate);
    }

    public StringProperty lessonDateProperty() {
        return lessonDate;
    }

    public int getDuration() {
        return duration.get();
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    public IntegerProperty durationProperty() {
        return duration;
    }

    public String getLessonTime() {
        return lessonTime.get();
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime.set(lessonTime);
    }

    public StringProperty lessonTimeProperty() {
        return lessonTime;
    }
}