package org.javaacademy.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "employees")
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "employees_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "salary")
    private BigDecimal salary;
    @Column
    private String position;
    @Enumerated(value = EnumType.STRING)
    @Column
    private Sex sex;
    @CreationTimestamp
    @Column
    private LocalDate createdDate;

    @OneToOne(mappedBy = "employee")
    private WorkBook workBook;

    @OneToMany(mappedBy = "father")
    private List<Child> childList;

    @JoinTable(
            name = "employee_entry_cabinet",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "cabinet_id")}

    )
    @ManyToMany
    private List<Cabinet> cabinets;

    public Employee(String firstName, String lastName, BigDecimal salary, String position, Sex sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.position = position;
        this.sex = sex;
    }
}
