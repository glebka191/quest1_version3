import java.math.BigDecimal;

public class Person implements Comparable<Person> {
        private String fullName;
        private BigDecimal salary;

    public Person(String fullname, BigDecimal salary) {
        this.fullName = fullname;
        this.salary = salary;
    }

    public String getFullname() {
        return fullName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullname='" + fullName + '\'' +
                ", salary=" + salary +
                '}';
    }
    @Override
    public int compareTo(Person o) {
       return this.getSalary().compareTo(o.getSalary());
    }
}
