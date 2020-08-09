package innotech;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class    CalculateVariants {
    public static final List<String> allTransfer = new ArrayList<>();
    public static BigDecimal getAverageSalary(List<Person> list) {
        BigDecimal averageSalary = new BigDecimal(0);
        for (Person person : list)
            averageSalary = averageSalary.add(person.getSalary());
        averageSalary = averageSalary.divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_UP);
        return averageSalary;
    }

    public static List<String> findVariants(List<Department> allDepartments){
        for (Department prOut : allDepartments) {
            List<List<Person>> allVariants = recursion(prOut.getPersonsList(),
                    0, new ArrayList<>(),
                    getAverageSalary(prOut.getPersonsList()));
            for (Department prIn : allDepartments) {
                if (prOut.getDepartmentName().equals(prIn.getDepartmentName()))
                    continue;
                for (List<Person> list : allVariants){
                    if (getAverageSalary(list).compareTo(getAverageSalary(prIn.getPersonsList())) > 0)
                        newData(prOut, prIn, list);
                }
            }
        }
        return allTransfer;
    }
    private static List<List<Person>> recursion(List<Person> finalList, int start, List<Person> list, BigDecimal averageSalaryOut){
        List<List<Person>> allVariants = new ArrayList<>();
        for (int i = start; i < finalList.size(); i++) {
            List<Person> nowList = new ArrayList<>(list);
            nowList.add(finalList.get(i));
            if (getAverageSalary(nowList).compareTo(averageSalaryOut) < 0){
                allVariants.add(nowList);
            }
            allVariants.addAll(recursion(finalList, i + 1, nowList, averageSalaryOut));
        }
        return allVariants;
    }

    public static void newData(Department prOut, Department pairIn, List<Person> list) {
        System.out.println("Откуда " + prOut.getDepartmentName() + " Куда " + pairIn.getDepartmentName());
        BigDecimal toSize = new BigDecimal(pairIn.getPersonsList().size());
        BigDecimal salaryAdd = pairIn.getAverageSalary().multiply(toSize);
        BigDecimal amount = new BigDecimal("0");
        for (Person person : list){
            amount = amount.add(person.getSalary());
        }
        BigDecimal listAvgSalary = amount.divide(new BigDecimal(list.size()),2,RoundingMode.HALF_UP);
        BigDecimal getAllSalaryList = listAvgSalary.multiply(new BigDecimal(list.size()));
        salaryAdd = salaryAdd.add(getAllSalaryList);
        BigDecimal newSize = new BigDecimal(pairIn.getPersonsList().size()+list.size());
        BigDecimal newSalaryAfterAdd = salaryAdd.divide(newSize,2,RoundingMode.HALF_UP);
        BigDecimal salaryRemove = new BigDecimal(prOut.getPersonsList().size()).multiply(prOut.getAverageSalary());
        salaryRemove = salaryRemove.subtract(getAllSalaryList);
        salaryRemove = salaryRemove.divide(BigDecimal.valueOf((prOut.getPersonsList().size() - list.size())), 2);
        String allLine = String.format("Откуда: %s Куда: %s \n Имя департамента: %s. Текущая средняя зарплата %s. Средняя зарплата после перевода: %s. \n Имя департамента: %s. Текущая средняя зарплата %s. Средняя зарплата после перевода: %s. \n",prOut.getDepartmentName(),pairIn.getDepartmentName(),prOut.getDepartmentName() , prOut.getAverageSalary() , salaryRemove,pairIn.getDepartmentName() , pairIn.getAverageSalary() , newSalaryAfterAdd);
        System.out.println("Имя департамента: " + prOut.getDepartmentName() + " Текущая средняя зарплата: " + prOut.getAverageSalary() + " Средняя зарплата после перевода: " + salaryRemove);
        System.out.println("Имя департамента: " + pairIn.getDepartmentName() + " Текущая зарплата: " + pairIn.getAverageSalary() + " Средняя заплата после перевода: " + newSalaryAfterAdd);
        System.out.println("Сотрудники: ");
        allTransfer.add(allLine);
        for (Person e : list) {
            System.out.println(e.getName() + " Откуда: " + prOut.getDepartmentName() + " Куда: " + pairIn.getDepartmentName() + " Зарплата:" + e.getSalary());
            String allPerson = String.format("%s Откуда: %s. Куда: %s. Зарплата: %s.\n", e.getName(), prOut.getDepartmentName(),pairIn.getDepartmentName(),e.getSalary());
            allTransfer.add(allPerson);
        }
    }
}

