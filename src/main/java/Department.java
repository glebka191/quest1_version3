import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_FLOOR;

public class Department {
    private static Map<String,Department> allDepartment = new HashMap<String, Department>();
    private List<Person> allPersonInDepartment;
    private String departmentName;

    public Department(String departName) {
        this.departmentName = departName;
        allPersonInDepartment = new ArrayList<Person>();
    }

    public static Map<String, Department> getAllDepartment() {
        return allDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public List<Person> getAllPersonInDepartment() {
        return allPersonInDepartment;
    }
    public BigDecimal averageSalaryCalculator() {
        BigDecimal amount = new BigDecimal("0");
        for (Person person : getAllPersonInDepartment()) {
            amount = amount.add(person.getSalary());
        }
        BigDecimal theOutcome = amount.divide(BigDecimal.valueOf(allPersonInDepartment.size()));
        return theOutcome;
    }
    public BigDecimal averageSalaryOnTransition(Person person, BigDecimal averageSalary){
        BigDecimal size = new BigDecimal(this.allPersonInDepartment.size());
        BigDecimal totalSalary = averageSalary.multiply(size);
        BigDecimal sizeaddone = size.add(new BigDecimal(1));
        BigDecimal newAverageSalaryAdd = (totalSalary.add(person.getSalary()));
        BigDecimal newAverageSalary = newAverageSalaryAdd.divide(sizeaddone,3,ROUND_FLOOR);
        return newAverageSalary;
    }
}
