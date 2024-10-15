package curso.java.hibernate.data.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TBL_TASK")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String taskName;
    @Column
    private String taskDescription;
    @Column
    private Integer employeeId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "scope_id")
    private Scope scope_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Scope getScope() {
        return scope_id;
    }

    public void setScope(Scope scope_id) {
        this.scope_id = scope_id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", employeeId=" + employeeId +
                ", scope_id=" + scope_id +
                '}';
    }
}
