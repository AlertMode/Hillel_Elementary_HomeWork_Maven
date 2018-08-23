package edu.palmirov.task_32;

/*
 * Task 32
 *
 * Вы реализуете одну из подзадач в рамках реализации игры, например, BlackJack.
 * Её суть в том, что метод должен возвращать нужный алгоритм в зависимость от внешних обстоятельств.
 *
 * Итак игрок человек хочет поиграть с AI (искуственный интелект) в BlackJack и выбирает уровень сложности.
 *
 * Предположим имеется такой enum:
 * public enum Complexity { GREEN, ADVANCE, GOD}
 * и следующая карта соответствий уровню сложности.
 *
 * GREEN — уровень новичок (AI)
 * ADVANCE — уровень опытный (AI)
 * EXPERT — уровень эксперт (AI)
 *
 * В игре есть такой функциональный интерфейс:
 *
 * @FunctionalInterface
 * public Strategy {
 * 	  boolean sayStop(int currentValue);
 * }
 *
 * Создать класс StategyHelper и реализовать статический метод selectStrategy(Complexity complexity) принимающий на вход один из возможных вариантов уровня сложности, а возвращающий при этом лямбда-функцию по следующему принципу в зависимости от уровня сложности:
 *
 * - для «GREEN» метод sayStop должен вернуть true, если currentValue>=13 (иначе false)
 * - для «ADVANCE» метод sayStop должен вернуть true, если currentValue>=15 (иначе false)
 * - для «EXPERT» метод say stop должен вернуть true, если currentValue>=17 (иначе false).
 *
 * Покрыть метод selectStrategy(ComplexetyAI complexity)  unit-тестами.
 *
 */

import java.util.Random;

public class Main {
    public static void main(String[] args){

        int first = randomNumber(1, 17);
        int second = randomNumber(1, 17);
        int third = randomNumber(1, 17);

        System.out.println("GREEN complexity: " + first + " : " +
                StrategyHelper.selectStrategy(Complexity.GREEN).sayStop(first) +
                "\nADVANCED complexity: " + second + " : " +
                StrategyHelper.selectStrategy(Complexity.ADVANCE).sayStop(second) +
                "\nEXPERT complexity: " + third + " : " +
                StrategyHelper.selectStrategy(Complexity.EXPERT).sayStop(third));
    }

    private static int randomNumber(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}