package edu.palmirov.task_32;

public class StrategyHelper {
    public static Strategy selectStrategy(Complexity complexity){
        Strategy temp = null;
        switch(complexity){
            case GREEN:
               temp = currentValue -> currentValue >= 13;
                break;
            case ADVANCE:
                temp = currentValue -> currentValue >= 15;
                break;
            case EXPERT:
                temp = currentValue -> currentValue >= 17;
                break;
        }
        return temp;
    }
}