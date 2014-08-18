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
    test("throwingNullTest", "Test if Objects.requireNonNull throws null when the given value is null.")
    .when(() -> {
      Objects.requireNonNull(null, "value cannot be null.");
    })
    .expect(throwing(NullPointerException.class)
           .containsMessage("cannot be null."));
    /* @formatter:on */
  }

  @Test
  public void testTestosterone2() throws Exception {
    final Runnable runnable = mock(Runnable.class);

    /* @formatter:off */
    test("verifyVoidMethod", "Check if Runnable.run() is called.")
    .when(() -> {
      runnable.run();
    })
    .then(() -> {
      verify(runnable, times(1)).run();
    });
    /* @formatter:on */

  }

  @Test
  public void testTestosterone3() throws Exception {
    /* given */
    final String input = "  result  ";
    final String expected = input.trim();

    /* @formatter:off */
    test("assertThat", "assert the actual result is equal to the expected")
    .when(() -> input.trim())
    .then(actual -> {
      assertThat(actual).isEqualTo(expected);
    });
    /* @formatter:on */
  }
}
