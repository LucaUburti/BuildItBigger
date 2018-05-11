package uby.luca.jokeslib;

import java.util.ArrayList;
import java.util.Random;

public class JokeTeller {
    private static String[] jokeList= {
            "Q. Why was King Arthur's army too tired to fight? A. It had too many sleepless knights.",
            "Did you hear about the semi-colon that broke the law? He was given two consecutive sentences.",
            "Do you think glass coffins will be a success? Remains to be seen.",
            "Q. Which country's capital has the fastest-growing population? A. Ireland. Every day it's Dublin.",
            "I asked my French friend if she likes to play video games. She said, \"Wii.\"",
            "If anyone needs an ark, I happen to Noah guy",
            "Yesterday, a clown held the door open for me. It was such a nice jester!",
            "The machine at the coin factory just suddenly stopped working, with no explanation. It doesn't make any cents",
            "Two fish are in a tank. One says to the other, \"Do you know how to drive this thing?\"",
            "Never criticize someone until you've walked a mile in their shoes. That way, when you criticize them, they won't be able to hear you from that far away. Plus, you'll have their shoes.",
            "The shovel was a ground-breaking invention.",
            "I wouldn't buy anything with velcro. It's a total rip-off."
    };
    public static String getJoke(){
      Random random=new Random();
      int randomNum=random.nextInt(jokeList.length);
      return jokeList[randomNum];
    }
}
