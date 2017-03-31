package li.clientmgt.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of lessons. This is used for saving the
 * list of lessons to XML.
 */
@XmlRootElement(name = "lessons")
public class LessonListWrapper {

    private List<Lesson> lessons;

    @XmlElement(name = "lesson")
    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}