package org.mokka88.commons.flow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecursionTest {
    @Test
    void testRecursion() {
        BosnianCoffee coffee = new BosnianCoffee();
        Fork<BosnianCoffee> isDone = new Fork<>(c -> c.isDone());
        Boil boilRepeatedly = new Boil();
        boilRepeatedly.next(new Cool()).next(isDone);

        new AddHotWater() //
                .next(isDone //
                        .fork(true, new Serve()) //
                        .fork(false, boilRepeatedly)) //
                .begin(coffee);

        assertEquals(false, coffee.isHot);
        assertEquals(true, coffee.isServed);
        assertEquals(true, coffee.isDone());
    }

    class BosnianCoffee {
        boolean isHot = false;
        int boiledTimes = 0;
        boolean isServed = false;

        void boil() {
            isHot = true;
            boiledTimes++;
        }

        boolean isDone() {
            return boiledTimes > 3;
        }
    }

    class AddHotWater extends AbstractLinearFlowComponent<BosnianCoffee> {
        @Override
        protected void businessLogic() {
            context.isHot = true;
        }
    }

    class Boil extends AbstractLinearFlowComponent<BosnianCoffee> {
        @Override
        protected void businessLogic() {
            context.boil();
        }
    }

    class Cool extends AbstractLinearFlowComponent<BosnianCoffee> {
        @Override
        protected void businessLogic() {
            context.isHot = false;
        }
    }

    class Serve extends AbstractLinearFlowComponent<BosnianCoffee> {
        @Override
        protected void businessLogic() {
            context.isServed = true;
        }
    }
}
