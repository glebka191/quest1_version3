package innotech;

import java.util.List;

public class Transfer {

    private static List<Department> allDepartments;

    public static void main(String[] args) {
        try {
            allDepartments = Validation.readDep(args[0]);
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Не указан путь для чтения");
        }

        if (allDepartments != null) {
            try {
                WriteFile.write(args[1], CalculateVariants.findVariants(allDepartments));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Не указан путь для записи");
            }
        }
    }
}
