package curso.java.hibernate;

import curso.java.hibernate.data.EmployeeRepository;
import curso.java.hibernate.data.entity.Employee;
import curso.java.hibernate.data.entity.Scope;
import curso.java.hibernate.data.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class HibernateExampleApp implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  EmployeeRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(HibernateExampleApp.class, args);
  }

  @Override
  public void run(String... args) throws Exception
  {
    Employee emp2 = new Employee();
    emp2.setEmail("new Employee email");
    emp2.setFirstName("Bart");
    emp2.setLastName("Simpson");

    emp2.setTasks(getTasks());

    repository.save(emp2);
    Optional<Employee> emp = repository.findById(2L);
    emp.ifPresent(employee -> logger.info("Employee id 2 -> {}", emp.get()));

    // Crear otro empleado
    Employee emp3 = new Employee();
    emp3.setEmail("new Employee email");
    emp3.setFirstName("Capitan");
    emp3.setLastName("America");
    emp3.setTasks(getTasks());
    repository.save(emp3);

    // Crear 10 empleados con las mismas tareas genericas
    for (int i = 0; i < 10; i++) {
      Employee empleado = new Employee();
      empleado.setEmail("new Employee email");
      empleado.setFirstName("Nombre " + String.valueOf(i));
      empleado.setLastName("Apellido " + String.valueOf(i));
      empleado.setTasks(getTasks());
      repository.save(empleado);
      empleado=null;
      System.out.println("Creado paciente: " + String.valueOf(i));
    }

    // Mostrar los empleados.
    repository.findAll().forEach(System.out::println);
  }

  private Set<Task> getTasks() {

    Scope scope1 = new Scope();
    scope1.setName("NameScope 1");
    scope1.setDescription("DescScope 1");
    //scopeRepository.save(scope1);

    Scope scope2 = new Scope();
    scope2.setName("NameScope 2");
    scope2.setDescription("DescScope 2");
    //scopeRepository.save(scope2);

    Set<Task> tasks = new HashSet<>();
    Task task1 = new Task();

    task1.setTaskName("T1");
    task1.setTaskDescription("Tarea 1");
    task1.setScope(scope1);
    tasks.add(task1);
    Task task2 = new Task();
    task2.setTaskName("T2");
    task2.setTaskDescription("Tarea 2");
    task2.setScope((scope2));
    tasks.add(task2);
    return tasks;
  }

}
