package com.practicaltask.employeeaccess.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "modules")
public class Modules {

    @Id
    @SequenceGenerator(name = "module_sequence",sequenceName = "module_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "module_sequence")
    private long id;

    @Column(name = "moduleName")
    private String moduleName;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "modules")
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

   public Modules(String moduleName){
        this.moduleName  = moduleName;
    }

    public Modules(long id, String moduleName) {
        this.id = id;
        this.moduleName = moduleName;
    }
}
