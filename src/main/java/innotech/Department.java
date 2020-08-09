package innotech;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Department {
    private List<Person> list = new ArrayList<>();
    private String departmentName;
    private static Map<String,Department> allDepartment = new HashMap<String, Department>();

    public static Map<String, Department> getAllDepartment() {
        return allDepartment;
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public List<Person> getPersonsList() {
        return new ArrayList<>(list);
    }

    public void addPerson(Person person) {
        list.add(person);
    }

    public BigDecimal getAverageSalary() {
        BigDecimal amount = new BigDecimal("0");
        for (Person person : getPersonsList()) {
            amount = amount.add(person.getSalary());
        }
        BigDecimal theOutcome = amount.divide(BigDecimal.valueOf(list.size()));
        return theOutcome;
    }
}