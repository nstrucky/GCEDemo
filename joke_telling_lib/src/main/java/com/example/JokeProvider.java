package com.example;

import java.security.SecureRandom;

public class JokeProvider {

        private String[] jokeList = {
                "I lost my job at the bank on my very first day. \u2013 A woman asked me to check" +
                        " her balance, so I pushed her over.",
                "A prisoner was told how he\u2019ll be executed. Needless to say, he was shocked.",
                "My poor knowledge of Greek mythology has always been my Achilles\u2019 elbow.",
                "How does Moses prepare his tea? \u2013 Hebrews it.",
                "Why don\u2019t you ever see hippopotamus hiding in trees? Because they\u2019re really good at it.",
                "A blind man walks into a bar. And a table. And a chair.",
                "Did you hear about the Mexican train killer? He had locomotives.",
                "You kill vegetarian vampires with a steak to the heart.",
                "If you want to catch a squirrel just climb a tree and act like a nut.",
                "The dyslexic devil worshipper sold his soul to Santa."

        };

        public JokeProvider() {};

        public String tellJoke() {
            SecureRandom random = new SecureRandom();
            int number = random.nextInt(jokeList.length);
            return jokeList[number];
        }
}
