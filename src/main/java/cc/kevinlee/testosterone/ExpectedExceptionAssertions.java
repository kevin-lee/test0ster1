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

import java.util.function.Function;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-08-12)
 *
 */
public class ExpectedExceptionAssertions<EX extends Throwable> {
  private final Class<? extends Throwable> expectedThrowable;
  private final Function<Throwable, ? extends Throwable> actualExceptionMapper;
  private final ExpectedExceptionAssertion<EX> expectedExceptionAssertion;

  /* @formatter:off */
  public ExpectedExceptionAssertions(final Class<? extends Throwable> expectedThrowable,
                                     final Function<Throwable, ? extends Throwable> actualExceptionMapper,
                                     final ExpectedExceptionAssertion<EX> expectedExceptionAssertion) {
    this.expectedThrowable = expectedThrowable;
    this.actualExceptionMapper = actualExceptionMapper;
    this.expectedExceptionAssertion = expectedExceptionAssertion;
  }
  /* @formatter:on */

  public Class<? extends Throwable> getExpectedThrowable() {
    return expectedThrowable;
  }

  public Function<Throwable, ? extends Throwable> getActualExceptionMapper() {
    return actualExceptionMapper;
  }

  public ExpectedExceptionAssertion<EX> getExpectedExceptionAssertion() {
    return expectedExceptionAssertion;
  }

  public ExpectedExceptionAssertions<EX> hasMessage(final String expectedMessage) {
    /* @formatter:off */
    return new ExpectedExceptionAssertions<>(
        expectedThrowable,
        actualExceptionMapper,
        expectedExceptionAssertion.andThen(
          (testResultHandler, throwable) -> {
            final Throwable actualException = actualExceptionMapper.apply(throwable);
            if (!actualException.getMessage()
                .equals(expectedMessage)) {
              throw new AssertionError(testResultHandler
                                      .getTestInfo() +
                                       "\nexpected: " + expectedMessage + " / actual: " +
                                       actualException.getMessage(),
                                       actualException);
            }
          }
        )
      );
    /* @formatter:on */
  }

  public ExpectedExceptionAssertions<EX> containsMessage(final String expectedMessage) {
    /* @formatter:off */
    return new ExpectedExceptionAssertions<>(
        expectedThrowable,
        actualExceptionMapper,
        expectedExceptionAssertion.andThen(
          (testResultHandler, throwable) -> {
            final Throwable actualException = actualExceptionMapper.apply(throwable);
            if (!actualException.getMessage()
                .contains(expectedMessage)) {
              throw new AssertionError(testResultHandler
                                      .getTestInfo() +
                                       "\nexpected: " + expectedMessage + " / actual: " +
                                       actualException.getMessage(),
                                       actualException);
            }
          }
        )
      );
    /* @formatter:on */
  }

  public <C extends Throwable> ExpectedExceptionAssertions<EX> causedBy(final Class<C> cause) {
    final Function<Throwable, ? extends Throwable> actualExceptionMapper = Throwable::getCause;
    /* @formatter:off */
    return new ExpectedExceptionAssertions<>(
        cause,
        actualExceptionMapper,
        expectedExceptionAssertion.andThen(
          (testResultHandler, throwable) -> {
            final Throwable actualException = actualExceptionMapper.apply(throwable);
            if (!cause.equals(actualException.getClass())) {
              throw new AssertionError(testResultHandler
                                      .getTestInfo() +
                                       "\ncause expected: " + cause + " / actual cause: " +
                                       actualException.getClass(),
                                       actualException);
            }
          }
        )
      );
    /* @formatter:on */
  }
}