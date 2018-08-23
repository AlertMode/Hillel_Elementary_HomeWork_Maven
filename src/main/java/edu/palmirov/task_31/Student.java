package edu.palmirov.task_31;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(id)
                .append(name)
                .append(age)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){ return false; }

        if(!Student.class.isAssignableFrom(obj.getClass())){
            return false;
        }

        final Student other = (Student) obj;
        if((this.name == null) ? (other.name != null) : !this.name.equals(other.name)){
            return false;
        }

        return (this.age == other.age) && (this.id == other.id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}