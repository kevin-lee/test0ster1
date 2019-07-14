package kevinlee.testosterone;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-07-02
 */
public class Desc {
  private Desc() {
  }

  public static String desc(final String message, final Object... args) {
    return String.format(message, args);
  }
}
