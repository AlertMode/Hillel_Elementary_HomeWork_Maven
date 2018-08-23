package edu.palmirov.task_39;

/*
* Task 39
* Создать 3 отдельных задач для выполнения в несколько потоков.
*
* I задача: вывести на экран строку “Hello To My Multithreading World:”.
*
* II задача: непрерывно вывести числа от строго от 1
* до 5 и затем “ start:” в одну строчку с пробельным знаком в качестве разделителя.
*
* III задача: попросить пользователя ввести его имя с помощью класса Scanner
* и сохранить результат в поле класса типа String.
*
* IV задача: вывести в консоль ранее сохраненное имя из поля класса с форматированием: “My name is %s.”
*
* Задачи I, II, III должны быть выполнены одновременно и неважно в какой последовательности.
* А задача IV должна быть выполнена строго после задачи III.  (Подсказка join() )
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try {
            threadsToGo();
        } catch (InterruptedException e) {
            System.err.println("Exception has occurred unexpectedly: " + e);
            e.printStackTrace();
        }
    }

    private static void threadsToGo() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            displayMessage("Hello To My Multithreading World!", true);
            Thread.yield();
        });

        Thread thread2 = new Thread(
                () -> {
                    for(int i = 1; i <= 5; i++){
                        displayMessage(String.valueOf(i) + " ", false);
                    }
                    displayMessage("start:", true);
                    Thread.yield();
                });

        class Name{
            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        Name name = new Name();
        Thread thread3 = new Thread(() -> {
            displayMessage("Enter the user's name!", true);
            name.setValue(new Scanner(System.in).next());
            Thread.yield();
        });

        Thread thread4 = new Thread(() -> {
            displayMessage(name.getValue(), true);
            Thread.yield();
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread3.join();
        thread4.start();

    }

    private static void displayMessage(String message, boolean addNewLine){
        System.out.printf("[%d :: %s] >> %s%s",
                Thread.currentThread().getId(),
                Thread.currentThread().getName(),
                message,
                addNewLine ? "\n" : "");
    }
}