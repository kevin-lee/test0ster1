/**
 * Copyright 2014 Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cc.kevinlee.testosterone;

import java.util.Objects;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-08-12)
 *
 */
public final class Testosterones {

  public static class ExpectedExceptionAssertions<EX extends Throwable> {
    private final Class<EX> expectedThrowable;

    private final ExpectedExceptionAssertion<EX> expectedExceptionAssertion;

    public ExpectedExceptionAssertions(final Class<EX> expectedThrowable, final ExpectedExceptionAssertion<EX> expectedExceptionAssertion) {
      this.expectedThrowable = expectedThrowable;
      this.expectedExceptionAssertion = expectedExceptionAssertion;
    }

    public Class<EX> getExpectedThrowable() {
      return expectedThrowable;
    }

    public ExpectedExceptionAssertion<EX> getExpectedExceptionAssertion() {
      return expectedExceptionAssertion;
    }

    public ExpectedExceptionAssertions<EX> hasMessage(final String expectedMessage) {
      expectedExceptionAssertion.andThen(throwable -> {
        if (!throwable.getMessage()
            .equals(expectedMessage)) {
          throw new AssertionError("expected: " + expectedMessage + " / actual: " + throwable.getMessage(), throwable);
        }
      });
      return this;
    }

    public ExpectedExceptionAssertions<EX> containsMessage(final String expectedMessage) {
      expectedExceptionAssertion.andThen(throwable -> {
        if (!throwable.getMessage()
            .contains(expectedMessage)) {
          throw new AssertionError("expected: " + expectedMessage + " / actual: " + throwable.getMessage(), throwable);
        }
      });
      return this;
    }
  }

  public static <EX extends Throwable> ExpectedExceptionAssertions<EX> throwing(final Class<EX> expectedThrowable) {
    return new ExpectedExceptionAssertions<EX>(expectedThrowable, throwable -> {
      if (!expectedThrowable.equals(throwable.getClass())) {
        throw new AssertionError("expected: " + expectedThrowable + " / actual: " + throwable.getClass(), throwable);
      }
    });
  }

  @FunctionalInterface
  public interface ExpectedExceptionAssertion<EX extends Throwable> {
    void assertThrowable(final EX throwable);

    default ExpectedExceptionAssertion<EX> andThen(final ExpectedExceptionAssertion<? super EX> after) {
      Objects.requireNonNull(after);
      return ex -> {
        this.assertThrowable(ex);
        after.assertThrowable(ex);
      };
    }
  }

  @FunctionalInterface
  public interface ExceptionExceptionHandler {

    Runnable getCodeBeingTested();

    default <EX extends Throwable> void expect(final ExpectedExceptionAssertions<EX> expectedExceptionAssertion) {
      boolean thrown = false;
      try {
        getCodeBeingTested().run();
      }
      catch (final Throwable e) {
        @SuppressWarnings("unchecked")
        final ExpectedExceptionAssertions<Throwable> expectedExceptionAssertions = (ExpectedExceptionAssertions<Throwable>) expectedExceptionAssertion;
        expectedExceptionAssertions.getExpectedExceptionAssertion()
            .assertThrowable(e);
        thrown = true;
      }
      if (!thrown) {
        throw new AssertionError("No exception was thrown when " + expectedExceptionAssertion.getExpectedThrowable()
            .getName() + " was expected");
      }
    }
  }

  public static ExceptionExceptionHandler when(final Runnable runnable) {
    return () -> runnable;
  }

  public Testosterones() throws IllegalAccessException {
    throw new IllegalAccessException(getClass().getName() + " cannot be instantiated.");
  }

}
