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

import static cc.kevinlee.testosterone.Testosterone.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Objects;

import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-08-12)
 *
 */
public class TestosteroneTest {
  @Test
  public void testTestosterone() throws Exception {
    /* @formatter:off */
    final String value = null;

    test("throwingNullTest",
         "Objects.requireNonNull(null, \"message\") should throw NullPointerException.")
    .when(() -> {
      Objects.requireNonNull(value, "value cannot be null.");
    })
    .expect(throwing(NullPointerException.class)
           .containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testTestosterone2() throws Exception {
    final Runnable innerRunnable1 = mock(Runnable.class);
    final Runnable innerRunnable2 = mock(Runnable.class);
    final Runnable runnable = () -> {
      innerRunnable1.run();
      innerRunnable2.run();
    };

    /* @formatter:off */
    test("verifyVoidMethod",
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
  public void testTestosterone3() {
    /* given */
    final String expected = "result";
    final String input = "  " + expected + "  ";

    /* @formatter:off */
    test("assertThat",
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
