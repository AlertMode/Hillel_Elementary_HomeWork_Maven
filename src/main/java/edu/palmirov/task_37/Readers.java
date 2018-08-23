package edu.palmirov.task_37;

import edu.palmirov.task_34.Gender;
import edu.palmirov.task_37.customExceptions.DataFieldsNumberException;
import edu.palmirov.task_37.customExceptions.InputValueException;
import edu.palmirov.task_37.enteties.Employee;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Readers {
    public static List<Employee> read(String fileName) throws DataFieldsNumberException,
            InputValueException,
            URISyntaxException
    {
        String path = getPathToResource(fileName);
        List<String> list = Readers.getFile(path);
        if(list == null || list.isEmpty()){
            return null;
        }
        List<Employee> employeeStream = new ArrayList<>();
        for(String line : list){
            Employee employee = new Employee();
            String[] values = line.split(",");
            int length = values.length;
            if(values.length > 5){
                throw new DataFieldsNumberException("The quantity of data fields exceeds 5 units!");
            } else if (values.length < 5){
                throw new DataFieldsNumberException("The quantity of data fields is below 5 units!");
            }
            for(int i = 0; i < length; i++){ separate(i, employee, values[i]); }
            employeeStream.add(employee);
        }
        return employeeStream;
    }

    private static void separate(int option, Employee employee, String value) throws InputValueException {
        value = value.replaceAll("\"", "").trim();
        switch(option){
            case 0: employee.setName(value);
                break;
            case 1:
                if(StringUtils.isNumeric(value)){
                    employee.setSalary(Integer.parseInt(value) / 100);
                } else {
                    throw new InputValueException("2nd field >> salary : " + value + " >> Not an integer value!");
                }
                break;
            case 2:
                if(StringUtils.isNumeric(value)){
                    employee.setAge(Integer.parseInt(value));
                } else {
                    throw new InputValueException("3rd field >> age : " + value + " >> Not an integer value!");
                }
                break;
            case 3:
                if(value.equalsIgnoreCase("male")){
                    employee.setGender(Gender.male);
                } else if (value.equalsIgnoreCase("female")){
                    employee.setGender(Gender.female);
                } else {
                    throw new InputValueException("4th field >> gender : " + value + " >> Not Gender-interface value");
                }
                break;
            case 4:
                if(value.equalsIgnoreCase("yes")) {
                    employee.setMarried(true);
                } else if(value.equalsIgnoreCase("no")){
                    employee.setMarried(false);
                } else {
                    throw new InputValueException("5th field >> married : " + value + " >> Not a boolean value");
                }
        }
    }

    private static String getPathToResource(String fileName) throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource(fileName).toURI();
        return Paths.get(uri).toString();
    }

    private static List<String> getFile(String fileName){
        List<String> result;
        try{
            result = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e){
            e.getMessage();
            e.printStackTrace();
            result = Collections.emptyList();
        }
        return result;
    }
}