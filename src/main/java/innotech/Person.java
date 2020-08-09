package innotech;

import java.math.BigDecimal;

public class Person {

    private BigDecimal salary;
    private String name;

    public Person(String name, BigDecimal salary) {
        this.name = name;
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getName(){ return name; }

    @Override
    public String toString() {
        return "Имя: " + name + ", Зарплата: " + salary;
    }
}
