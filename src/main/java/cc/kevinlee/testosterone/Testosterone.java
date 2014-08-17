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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-08-17)
 *
 */
public class Testosterone {
  private final AtomicInteger testNumberGenerator = new AtomicInteger();
  private final String name;
  private final String description;

  public Testosterone(final String name, final String descripton) {
    this.name = name;
    this.description = descripton;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public RunnableTestResultHandler when(final Runnable runnable) {
    return new RunnableTestResultHandler(testNumberGenerator.getAndIncrement(), this, runnable);
  }

  public <T> CallableTestResultHandler<T> when(final Callable<T> callable) {
    return new CallableTestResultHandler<T>(testNumberGenerator.getAndIncrement(), this, callable);
  }
}
