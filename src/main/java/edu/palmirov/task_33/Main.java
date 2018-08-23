package edu.palmirov.task_33;

/*
 * Task 33
 *
 * Напоминаю, что ссылка на метод передаётся по следующим правилам
 * Name_of_class::name_of_static_method
 * или в случае, если метод не статический, то
 * Instance_of_Object::name_of_method
 *
 * Например, есть всем известный статический метод System.out.println(String s);
 * Который принимает параметр String-параметр и при этом ничего не возвращает.
 * Представим, что также имеется такой functionalInterface вида:
 *
 * @FunctionalInterface
 * public DoOnlyAction {
 *      void todo(String s);
 * }
 *
 * Имеется класс Worker с методом void doAction(DoOnlyAction action), который будет реализовывать операции, которые вы передадите ему на вход таким образом:
 *
 * public class Worker {
 *   public static void act(DoOnlyAction action, String s) {
 *      action.todo(action);
 *   }
 * }
 *
 * Таким образом Worker будет выполнять любую работу, которую вы скажете ему, при этом выполняя задания заранее не имея представления о точном ее характере.
 * Например, так:
 *
 * public static void main(String[] args) {
 *     Worker.act(System.out::println, "I'm the best!");
 * }
 *
 * Передача метода по ссылке(::) возможна в данной ситуации благодаря тому, что полностью совпадают сигнатуры у метода void todo(String s) интерфейса DoAction с сигнатурой метода
 * System.out.println(String s)
 * Оба принимает тип String и ничего не возвращают!
 *
 *
 * Таким образом тип DoOnlyAction может работать и с любыми другими методами, которые совпадают с сигнатурой единственного метода
 * void todo(String s).
 *
 * Можете попробовать и сами в этом удостовериться :)
 *
 * ЗАДАЧА:
 * Итак, создать такой:
 *
 * @FunctionalInterface
 * public interface DoOnlyAction<T> {
 *   T todo(String s);
 * }
 *
 * и
 *
 * public class StringWorker {
 *   public static<T> T act(DoOnlyAction action, String s) {
 *       return (T) action.todo(s);
 *    }
 * }
 *
 * А также предположим есть такой класс:
 *
 * public class StringAlg {
 *    // your method bellow
 *    public int getNumberParts(String s) {
 *    // your code here
 *   }
 * }
 *
 * Реализуйте метод public int getNumberParts(String s), который подсчитывает кол-во частей в некоторой строке по разделителю «,» (запятая).
 * Например, если на вход подать строку «Ola, Ola, Ola», то метод должен вернуть значение 3 для искомой строки.
 *
 * Напишите клиентский класс и поручите выполнение этой работы Worker’у используя метод act(DoOnlyAction action, String s) передав в него ссылку на метод getNumberParts используя оператор (::), а также саму строку вторым аргументом, в которой собственно необходимо подсчитать количество подчастей по заранее заданному разделителю.
 * Протестируйте ваш клиентский код с помощью unit-test.
 */

public class Main {
    public static void main(String[] args){
        System.out.println((int)StringWorker.act(StringAlg::getNumberParts, "Ola, Ola, Ola"));
    }
}