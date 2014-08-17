Test0ster1 (Testosterone)
=========================

Test0ster1 (pronounced as 'Testosterone', Testosterone -> Test0sterone -> Test0ster(one == 1) -> Test0ster1 ) is a simple test helper framework which uses Java 8's new functional features (i.e. Lambda Expressions, Default Methods, etc.) in order to provide simple and easy to use test tools.

## Why is it called 'Testosterone'?

1. It has 'test' in it **Test**-osterone.
2. The binary numbers, 0 and 1, which have something to do with software, are found in 'testosterone' (Test-**o**-ster-**one** => Test-**0**-ster-**1**).
3. The goal of this project is to provide a simple but powerful test framework just like testosterone makes men simple (or often called stupid :) ) and physically strong.


## What does it look like?

* test an expected exception

```java
test("throwingNullTest", "Test if Objects.requireNonNull throws null when the given value is null.")
.when(() -> {
  Objects.requireNonNull(null, "The value cannot be null.");
})
.expect(throwing(NullPointerException.class)
       .containsMessage("cannot be null"));
```

* test a void return type method (with [Mockito](https://github.com/mockito/mockito))

```java
test("verifyVoidMethod", "Check if Runnable.run() is called.")
.when(() -> {
  runnable.run();
})
.then(() -> {
  verify(runnable, times(1)).run();
});
```

* test a method which returns some result (with [AssertJ](http://joel-costigliola.github.io/assertj/))

```java
/* given */
final String input = "  result  ";
final String expected = input.trim();

test("TestTrim", "assert the actual result is equal to the expected")
.when(() -> input.trim())
.then(actual -> {
  assertThat(actual).isEqualTo(expected);
});

```