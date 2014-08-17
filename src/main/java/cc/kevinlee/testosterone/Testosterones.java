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
public final class Testosterones {

  public static <EX extends Throwable> ExpectedExceptionAssertions<EX> throwing(final Class<EX> expectedThrowable) {
    return new ExpectedExceptionAssertions<EX>(expectedThrowable, (testResultHandler, throwable) -> {
      if (!expectedThrowable.equals(throwable.getClass())) {
        throw new AssertionError(testResultHandler.getTestInfo() + "\nexpected: " + expectedThrowable + " / actual: "
            + throwable.getClass(), throwable);
      }
    });
  }

  public static Testosterone test(final String name, final String description) {
    return new Testosterone(name, description);
  }

  public Testosterones() throws IllegalAccessException {
    throw new IllegalAccessException(getClass().getName() + " cannot be instantiated.");
  }

}
