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

final String value = null;

test("throwingNullTest",
     "Objects.requireNonNull(null, \"message\") should throw NullPointerException.")
.when(() -> {
  Objects.requireNonNull(value, "value cannot be null.");
})
.expect(throwing(NullPointerException.class)
       .containsMessage("cannot be null"));

```

* test a void return type method (with [Mockito](https://github.com/mockito/mockito))

```java

/* given */
final Runnable innerRunnable1 = mock(Runnable.class);
final Runnable innerRunnable2 = mock(Runnable.class);
final Runnable runnable = () -> {
  innerRunnable1.run();
  innerRunnable2.run();
};

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

```

```java

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

```

## Get Test0ster1
### Maven
* Add maven repository
```
<repositories>
  ...

  <repository>
    <name>Kevin's Public Releases</name>
    <url>http://nexus.lckymn.com/content/repositories/kevin-public-releases</url>
  </repository>

  ...
</repositories>
```
* Add dependency
```
<dependencies>
	...

  <dependency>
    <groupId>cc.kevinlee</groupId>
    <artifactId>testosterone</artifactId>
    <version>0.0.4</version>
    <scope>test</scope>
  </dependency>

	...
</dependencies>
```