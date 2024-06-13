package org.javaacademy.hibernate;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.javaacademy.hibernate.entity.Cabinet;
import org.javaacademy.hibernate.entity.Child;
import org.javaacademy.hibernate.entity.Employee;
import org.javaacademy.hibernate.entity.Sex;
import org.javaacademy.hibernate.entity.WorkBook;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import static java.math.BigDecimal.TEN;
import static org.javaacademy.hibernate.entity.Sex.MALE;

public class Runner2 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/hibernate");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "terrrr");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);

        @Cleanup SessionFactory sessionFactory = new Configuration().addProperties(properties)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(WorkBook.class)
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Cabinet.class)
                .buildSessionFactory();

        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employee employee = new Employee("yuri", "ivanov", TEN, "manager", MALE);
        session.persist(employee);
        WorkBook workBook = new WorkBook("123456", employee);
        session.persist(workBook);

        Child rebecca = new Child("rebecca", employee);
        Child petr = new Child("petr", employee);
        session.persist(rebecca);
        session.persist(petr);

        Cabinet cabinet = new Cabinet(101, List.of(employee));
        session.persist(cabinet);

        session.getTransaction().commit();
        session.clear();

        Employee employee1 = session.get(Employee.class, 1);
        System.out.println(employee1);

    }
}
