package org.javaacademy.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "work_book")
@NoArgsConstructor
public class WorkBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String number;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @ToString.Exclude
    private Employee employee;

    public WorkBook(String number, Employee employee) {
        this.number = number;
        this.employee = employee;
    }
}
