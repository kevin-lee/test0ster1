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
package testosterone;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Objects;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-08-12)
 *
 */
public class TestosteroneTest {
  @Test
  public void testTestosteroneExpectingException() throws Exception {
    /* @formatter:off */
    /* Given */
    final String value = null;

    Testosterone.test("throwingNullTest",
         "Objects.requireNonNull(null, \"message\") should throw NullPointerException.")
    .when(() -> {
      Objects.requireNonNull(value, "value cannot be null.");
    })
    .expect(Testosterone.throwing(NullPointerException.class)
           .hasMessage("value cannot be null.")
           .containsMessage("cannot be null"));
    /* @formatter:on */
  }

  private void throwRuntimeException(final String value) {
    if (value == null) {
      throw new RuntimeException("test exception", new NullPointerException("value cannot be null."));
    }
  }

  @Test
  public void testTestosteroneExpectingExceptionWithCause() throws Exception {
    /* @formatter:off */
    /* Given */
    final String value = null;

    Testosterone.test("throwingNullTest2",
        "throwRuntimeException(null) should throw RuntimeException caused by NullPointerException.")
    .when(() ->
      throwRuntimeException(value)
    )
    .expect(Testosterone.throwing(RuntimeException.class)
           .hasMessage("test exception")
           .containsMessage("test ")
           .causedBy(NullPointerException.class)
           .containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testVerifyingVoidMethods() throws Exception {
    /* Given */
    final Runnable innerRunnable1 = mock(Runnable.class);
    final Runnable innerRunnable2 = mock(Runnable.class);
    final Runnable runnable = () -> {
      innerRunnable1.run();
      innerRunnable2.run();
    };

    /* @formatter:off */
    Testosterone.test("verifyVoidMethod",
         "innerRunnable1.run() and innerRunnable2.run() should be invoked when runnable.run().")
    .when(() -> {
      runnable.run();
    })
    .then(() ->
      verify(innerRunnable1, times(1)).run()
    )
    .then(() -> {
      verify(innerRunnable2, times(1)).run();
    });
    /* @formatter:on */

  }

  private String nullSafeTrim(final String value) {
    return value == null ? "" : value.trim();
  }

  @Test
  public void testNullSafeTrim() {
    /* given */
    final String expected = "result";
    final String input = "  " + expected + "  ";

    /* @formatter:off */
    Testosterone.test("testNullSafeTrim",
         "nullSafeTrim(\"  result  \") should return \"result\".")
    .when(() ->
      nullSafeTrim(input)
    )
    .then(actual ->
      assertThat(actual.length()).isEqualTo(expected.length())
    )
    .then(actual -> {
      assertThat(actual).isEqualTo(expected);
    });
    /* @formatter:on */
  }
}
