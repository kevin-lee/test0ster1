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

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-08-12)
 *
 */
public class ExpectedExceptionAssertions<EX extends Throwable> {
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
    expectedExceptionAssertion.andThen((testResultHandler, throwable) -> {
      if (!throwable.getMessage()
          .equals(expectedMessage)) {
        throw new AssertionError(testResultHandler.getTestInfo() + "\nexpected: " + expectedMessage + " / actual: " + throwable.getMessage(),
            throwable);
      }
    });
    return this;
  }

  public ExpectedExceptionAssertions<EX> containsMessage(final String expectedMessage) {
    expectedExceptionAssertion.andThen((testResultHandler, throwable) -> {
      if (!throwable.getMessage()
          .contains(expectedMessage)) {
        throw new AssertionError(testResultHandler.getTestInfo() + "\nexpected: " + expectedMessage + " / actual: " + throwable.getMessage(),
            throwable);
      }
    });
    return this;
  }
}