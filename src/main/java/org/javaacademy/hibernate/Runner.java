package org.javaacademy.hibernate;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javaacademy.hibernate.entity.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import org.hibernate.cfg.Environment;
import org.javaacademy.hibernate.entity.Passport;
import org.javaacademy.hibernate.entity.Sex;
import org.javaacademy.hibernate.entity.WorkBook;

public class Runner {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/hibernate");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "terrrr");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);

        @Cleanup SessionFactory sessionFactory = new Configuration().addProperties(properties)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(WorkBook.class)
                .buildSessionFactory();

        @Cleanup Session session = sessionFactory.openSession();
        //Создать сотрудника
        //createEmployee(session);
        //createEmployee(session, "Ivan", "Ivanov", "worker", Sex.MALE);
        //createEmployee(session, "Glasha", "Petrova", "worker", Sex.FEMALE);

        //Получить по id
        //Employee employee = session.find(Employee.class, 1);
        //System.out.println(employee);

        //Получить список сотрудников
        //HQL
        //Hibernate query language
        List<Employee> employees = session.createQuery("from Employee", Employee.class).list();

        //Чистый sql запрос
//        List<Employee> employees = session.createNativeQuery("select * from employees", Employee.class).list();
        System.out.println(employees);

        //Удаление сотрудника
//        Employee employee = session.find(Employee.class, 1);
//        session.beginTransaction();
//        session.remove(employee);
//        session.getTransaction().commit();

        //Удаление сотрудника через HQL
//        session.beginTransaction();
//        session.createQuery("delete from Employee where id = 2").executeUpdate();
//        session.getTransaction().commit();

    }

    private static void createEmployee(Session session, String name, String lastName,
                                       String position, Sex sex) {
        session.beginTransaction();
        Employee employee = new Employee(name, lastName, BigDecimal.TEN, position, sex);
        session.persist(employee);
        session.getTransaction().commit();
    }
}
