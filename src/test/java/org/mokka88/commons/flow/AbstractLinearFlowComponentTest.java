package org.mokka88.commons.flow;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AbstractLinearFlowComponentTest {

    @Test
    void testLinearFlow() {
        TestData data = new TestData();

        new First() //
                .next(new Second()) //
                .begin(data);

        assertEquals(1, data.first);
        assertEquals(2, data.second);
    }

    class TestData {
        int first = 0;
        int second;
    }

    class First extends AbstractLinearFlowComponent<TestData> {
        @Override
        protected void businessLogic() {
            context.first++;
        }
    }

    class Second extends AbstractLinearFlowComponent<TestData> {
        @Override
        protected void businessLogic() {
            context.second = context.first + 1;
        }
    }
}