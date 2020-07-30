import java.io.*;
import java.util.Collections;
import java.util.Map;

public class Transfer {
    public static void main(String[] args) throws IOException, Distribution.ValidationException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        while (reader.ready()) {
            String allLine = reader.readLine();
            if (!allLine.equals("")) {
                Distribution.distributionValidation(allLine);
            }
        }
        for (Map.Entry<String, Department> pr : Department.getAllDepartment().entrySet()) {
            pr.getValue().averageSalaryCalculator();
            Collections.sort(pr.getValue().getAllPersonInDepartment());
        }

        for (Map.Entry<String, Department> pr : Department.getAllDepartment().entrySet()){
            Distribution.redistribution(pr.getValue());
        }

        try {
            writer.write(Distribution.result.toString());
        }
        catch (IOException e){
            System.out.println("Ошибка при записи");
        }
        finally {
            writer.flush();
            writer.close();
            reader.close();
        }
    }
}