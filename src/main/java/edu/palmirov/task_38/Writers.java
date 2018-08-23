package edu.palmirov.task_38;

import edu.palmirov.task_34.Gender;
import edu.palmirov.task_37.enteties.Employee;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Writers {
    private static final String CSV_SEPARATOR = ",";
    private static final String CSV_QUOTATIONS = "\"";

    public static void writeToCSV(List<Employee> employees) {
        List<Employee> processedList = change(employees);
        int count = 0;
        try
        {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("res/output.csv"), "UTF-8"));
            for (Employee employee : processedList)
            {
                ++count;
                StringBuilder line = new StringBuilder();
                line.append(CSV_QUOTATIONS);
                line.append(employee.getName());
                line.append(CSV_QUOTATIONS + CSV_SEPARATOR + " ");
                line.append(employee.getSalary());
                line.append(CSV_SEPARATOR + " ");
                line.append(employee.getAge());
                line.append(CSV_SEPARATOR + " " + CSV_QUOTATIONS);
                line.append(employee.getGender() == Gender.male ? "male" : "female");
                line.append(CSV_QUOTATIONS + CSV_SEPARATOR + " " + CSV_QUOTATIONS);
                line.append(employee.isMarried() ? "yes": "no");
                line.append(CSV_QUOTATIONS);
                bw.write(line.toString());
                if(employees.size() > count){
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {
            System.err.println("The Character Encoding is not supported!");
            e.printStackTrace();
        } catch (FileNotFoundException e){
            System.err.println("The file requested isn't found!");
            e.printStackTrace();
        } catch (IOException e){
            System.err.println("I/O error has occurred unexpectedly!");
            e.printStackTrace();
        }
    }

    private static List<Employee> change(List <Employee> employees) {
        return employees.stream().peek(e -> {
            if (e.getAge() > 25 && e.getAge() < 30){
                e.setSalary(e.getSalary() * 100 + (250 * 100));
            } else if (e.getAge() >= 30){
                e.setSalary(e.getSalary() * 100 + (500 * 100));
            } else {
                e.setSalary(e.getSalary() * 100);
            }
        }).collect(Collectors.toList());
    }
}