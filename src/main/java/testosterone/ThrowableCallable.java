package testosterone;

/**
 * @author Kevin Lee
 * @since 2020-11-04
 */
public interface ThrowableCallable<T> {
  T call() throws Throwable;
}
