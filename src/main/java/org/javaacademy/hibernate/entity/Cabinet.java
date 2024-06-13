package org.javaacademy.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "cabinet")
@NoArgsConstructor
public class Cabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer number;


    @ManyToMany(mappedBy = "cabinets")
    @ToString.Exclude
    private List<Employee> employeeList;

    public Cabinet(Integer number, List<Employee> employeeList) {
        this.number = number;
        this.employeeList = employeeList;
    }
}
