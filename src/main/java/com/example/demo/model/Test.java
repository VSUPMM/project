package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.ALL;

@Data
@Entity
@Table(name = "test", schema = "public")
public class Test {

  @Id @GeneratedValue
  @Column(name = "testId")
  private  Long testId;

  @Column(name = "name")
  private String name;

  @JsonManagedReference(value="PracticalTask-movement")
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(mappedBy="practTaskTest", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  private List<PracticalTask> practicalTasks ;

  @JsonManagedReference(value="TestTask-movement")
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(mappedBy="testTaskTest", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  private List<TestTask> testTasks ;


  @JsonManagedReference(value="testResult-movement")
  @OneToMany(mappedBy="testId", cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
  private List<TestResult> testResults ;

  public Test() {}

  public Test(String name) {
    this.name = name;
  }

}

