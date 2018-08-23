package edu.palmirov.task_34;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Employee {
   private String name;
   private long salary;
   private int age;
   private Gender gender;
   private boolean married;

    public Employee(String name, Long salary, int age, Gender gender, boolean married) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.gender = gender;
        this.married = married;
    }

    public String getName() {
        return name;
    }

    public Long getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isMarried() {
        return married;
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder(17, 31)
                .append(name)
                .append(salary)
                .append(age)
                .append(gender)
                .append(married)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Employee.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Employee other = (Employee) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }

        return this.salary == other.salary
                && this.age == other.age
                && this.gender == other.gender
                && this.married == other.married;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", gender=" + gender +
                ", married=" + married +
                '}';
    }
}