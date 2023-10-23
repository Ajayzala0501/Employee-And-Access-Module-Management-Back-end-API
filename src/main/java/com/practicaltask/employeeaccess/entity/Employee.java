package com.practicaltask.employeeaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee")
@Builder
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_sequence",sequenceName = "employee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_sequence")
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    public Employee(long id, String firstName, String lastName, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    @Column(name = "department")
    private String department;



    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "employee_module_mapping",joinColumns = @JoinColumn(name = "employee_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "module_id",referencedColumnName = "id"))
    private Set<Modules> modules = new HashSet<>();

    public void add(Modules m){
        this.modules.add(m);
         m.getEmployees().add(this);
    }

    public void removeModule(long id){
        Modules module = this.modules.stream().filter(m -> m.getId()==id).findFirst().orElse(null);
        if(module != null){
            this.modules.remove(module);
            module.getEmployees().remove(this);
        }
    }
}
