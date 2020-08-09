package innotech;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Validation {
    public static List<Department> readDep(String fileName) {
        int numStr = 0;
        String[] arrayData = new String[0];
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))){
            String line = fileReader.readLine();
            while (line != null) {
                numStr++;
                try {
                    arrayData = line.split("; ");
                    if (arrayData.length != 3)
                        throw new IndexOutOfBoundsException();
                    if (arrayData[0].isEmpty() || arrayData[0].matches(".*[\\d^!@#$%&*]+.*")){
                        throw new ValidationException("Некорректно указаны ФИО",numStr);
                    }
                    BigDecimal salary = new BigDecimal(arrayData[2]);
                    if (salary.compareTo(BigDecimal.ZERO) < 0 || salary.scale() > 2)
                        throw new NumberFormatException();
                    Person person = new Person(arrayData[0],salary);
                    String departmentName = arrayData[1].trim();
                    if (!Department.getAllDepartment().containsKey(departmentName))
                        Department.getAllDepartment().put(departmentName, new Department(departmentName));
                    Department.getAllDepartment().get(departmentName).addPerson(person);
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("Неверно указан сотрудник " + numStr);
                }
                catch (NumberFormatException e){
                    System.out.println("Неверно указана зарплата "+ numStr);
                } catch (ValidationException e) {
                    return null;
                }
                line = fileReader.readLine();
            }
        } catch (IOException e){
            System.out.println("Ошибка чтения");
            return null;
        }

        List<Department> allLists = new ArrayList<>(Department.getAllDepartment().values());
        return allLists;
    }
}
