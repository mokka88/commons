package org.mokka88.commons.flow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForkTest {

    public static final String GREET_ENGLISH = "Hello";
    public static final String GREET_GERMAN = "Hallo";
    public static final String GREET_HUNGARIAN = "Szeva";

    @Test
    void testFork() {
        TestData data = new TestData();
        data.language = Language.HUNGARIAN;

        new Fork<TestData>(d -> d.language) //
        .fork(Language.GERMAN, new GreetInGerman()) //
        .fork(Language.ENGLISH, new GreetInEnglish()) //
        .fork(Language.HUNGARIAN, new GreetInHungarian()) //
        .begin(data);

        assertEquals(GREET_HUNGARIAN, data.greeting);
    }

    class TestData {
        Language language;
        String greeting;
    }

    enum Language {
        GERMAN,
        ENGLISH,
        HUNGARIAN
    }

    class GreetInEnglish extends AbstractLinearFlowComponent<TestData> {
        @Override
        protected void implementation() {
            data.greeting = GREET_ENGLISH;
        }
    }

    class GreetInGerman extends AbstractLinearFlowComponent<TestData> {
        @Override
        protected void implementation() {
            data.greeting = GREET_GERMAN;
        }
    }

    class GreetInHungarian extends AbstractLinearFlowComponent<TestData> {
        @Override
        protected void implementation() {
            data.greeting = GREET_HUNGARIAN;
        }
    }
}