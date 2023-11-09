package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("employee")
public class Employee extends User{

    @Column(name = "employee_id")
    private Long employeeId;
}
