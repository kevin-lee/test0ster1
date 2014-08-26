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
public class RunnableTestResultHandler extends AbstractTestResultHandler<Runnable> implements TestResultHandler<Runnable> {

  private final Runnable runnable;

  public RunnableTestResultHandler(final int testNumber, final Testosterone testosterone, final Runnable runnable) {
    super(testNumber, testosterone);
    this.runnable = runnable;
  }

  Runnable getCodeBeingTested() {
    return runnable;
  }

  @Override
  public <EX extends Throwable> void expect(final ExpectedExceptionAssertions<EX> expectedExceptionAssertion) {
    boolean thrown = false;
    try {
      getCodeBeingTested().run();
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
  public ThenAfterRunning then(final Runnable thenDo) {
    getCodeBeingTested().run();
    try {
      thenDo.run();
      return new ThenAfterRunning(this);
    }
    catch (final Throwable e) {
      throw new TestInfoAddedAssertionError(this.getTestInfo(), e);
    }
  }
}