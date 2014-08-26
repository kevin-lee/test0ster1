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

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-08-16)
 *
 */
public class CallableTestResultHandler<T> extends AbstractTestResultHandler<Consumer<T>> implements ThrowableTestResultHandler<Consumer<T>> {

  private final Callable<T> callable;

  public CallableTestResultHandler(final int testNumber, final Testosterone testosterone, final Callable<T> callable) {
    super(testNumber, testosterone);
    this.callable = callable;
  }

  Callable<T> getCodeBeingTested() {
    return callable;
  }

  @Override
  public <EX extends Throwable> void expect(final ExpectedExceptionAssertions<EX> expectedExceptionAssertion) {
    boolean thrown = false;
    try {
      getCodeBeingTested().call();
    }
    catch (final Throwable e) {
      @SuppressWarnings("unchecked")
      final ExpectedExceptionAssertions<Throwable> expectedExceptionAssertions = (ExpectedExceptionAssertions<Throwable>) expectedExceptionAssertion;
      expectedExceptionAssertions.getExpectedExceptionAssertion()
          .assertThrowable(this, e);
      thrown = true;
    }
    if (!thrown) {
      throw new AssertionError(this.getTestInfo() + "\nNo exception was thrown when " + expectedExceptionAssertion.getExpectedThrowable()
          .getName() + " was expected");
    }
  }

  @Override
  public ThenAfterCalling<T> then(final Consumer<T> then) {
    try {
      final T actual = getCodeBeingTested().call();
      then.accept(actual);
      return new ThenAfterCalling<T>(this, actual);
    }
    catch (final Throwable e) {
      throw new TestInfoAddedAssertionError(this.getTestInfo(), e);
    }
  }
}
