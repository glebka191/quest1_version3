
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Distribution {
    static List<String> result = new ArrayList<>();
    public static void distributionByDepartment(String name, String department, BigDecimal salary) {
        Person person = new Person(name, salary);
        distribute(department, person);
    }


    public static void distributionValidation(String allLine) throws ValidationException {
        try {
            String[] arrayData = allLine.split(", ");
            if (arrayData.length != 3) {
                throw new ValidationException("В строке больше или меньше 3-х элементов");
            }
            if (arrayData[0].isEmpty() || arrayData[0].matches(".*[\\d^!@#$%&*]+.*")) {
                throw new ValidationException("Некорректно указаны ФИО");

            }
            if (arrayData[1].isEmpty() || arrayData[1].matches(".*[\\d^!@#$%&*]+.*")) {
                throw new ValidationException("Некорректно указан департамент");
            }
            if (arrayData[2].isEmpty() || arrayData[2].matches("[\\D^!@#$%&*]+.*")) {
                throw new ValidationException("Некорректная зарплата");
            } else {
                String name = arrayData[0].toUpperCase();
                String depart = arrayData[1].toUpperCase();
                BigDecimal salary = new BigDecimal(arrayData[2]);
                distributionByDepartment(name, depart, salary);
            }
        }catch (NumberFormatException e){
            System.out.println("Некорректно указано значение зарплаты");
        }
    }

    static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }

    public static void distribute(String distributionName, Person person) {
        if (Department.getAllDepartment().containsKey(distributionName)) {
            Department dp = Department.getAllDepartment().get(distributionName);
            dp.getAllPersonInDepartment().add(person);
        } else {
            Department department = new Department(distributionName);
            Department.getAllDepartment().put(distributionName, department);
            department.getAllPersonInDepartment().add(person);
        }
    }


    public static void redistribution(Department department) {
        BigDecimal averageSalaryFromWhere = department.averageSalaryCalculator();
        List<Person> personsInDepartment = department.getAllPersonInDepartment();
        for (int i = 0; i < personsInDepartment.size(); i++) {
            int result = personsInDepartment.get(i).getSalary().compareTo(averageSalaryFromWhere);
            if (result >= 0) {
                return;
            } else {
                comparisonWithOtherDepartments(personsInDepartment.get(i), department.getDepartmentName());
            }
        }
    }

    public static void comparisonWithOtherDepartments(Person personInDepartment, String departmentNameFromWhere) {
        for (Map.Entry<String, Department> pr : Department.getAllDepartment().entrySet()) {
            if (!pr.getValue().getDepartmentName().equals(departmentNameFromWhere)) {
                BigDecimal averageSalaryInCheckedDepartment = pr.getValue().averageSalaryCalculator();
                int result = personInDepartment.getSalary().compareTo(averageSalaryInCheckedDepartment);
                if (result > 0) {
                    newData(personInDepartment.getFullname(), departmentNameFromWhere, pr.getValue().getDepartmentName(),
                            averageSalaryInCheckedDepartment, pr.getValue().averageSalaryOnTransition(personInDepartment, averageSalaryInCheckedDepartment));
                }
            }
        }
    }

    public static void newData(String personName, String oldNameDepartment, String newNameDepartment,
                               BigDecimal oldAverageSalary, BigDecimal newAverageSalary) {
            String allLine = String.format("%s перешёл из %s в %s. Старая зп в %s была - %.2f, новая %.2f\n", personName,
                    oldNameDepartment, newNameDepartment, newNameDepartment, oldAverageSalary, newAverageSalary);
            result.add(allLine);
    }
}