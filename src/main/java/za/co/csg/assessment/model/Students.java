package za.co.csg.assessment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name="STUDENTS")
@ApiModel(description="details about Students.")
@JsonPropertyOrder({ "id", "studentName", "createdDate", "className", "grade", "isPresent" })
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column(name = "createdDate", nullable = false)
    private LocalDateTime createdDate;
    @Column(name = "studentName")
    private String studentName;
    @Column(name = "className")
    private String className;
    @Column(name = "grade")
    private String grade;
    @Column(name = "isPresent")
    private boolean isPresent;

    public Students() {
        super();
    }

    public Students(Long id, String studentName, LocalDateTime createdDate, String className, String grade, boolean isPresent  ) {
        super();
        this.id = id;
        this.studentName = studentName;
        this.createdDate = createdDate;
        this.className= className;
        this.grade = grade;
        this.isPresent=isPresent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }


}

