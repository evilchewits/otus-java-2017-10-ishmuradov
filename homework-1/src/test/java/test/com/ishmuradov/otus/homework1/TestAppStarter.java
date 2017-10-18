package test.com.ishmuradov.otus.homework1;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import com.ishmuradov.otus.homework1.AppStarter;

public class TestAppStarter {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

  /**
   * A helper function: returns the latest not empty string from System.out
   * 
   * @return
   */
  private String getLatestOutputLine() {
    return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(outContent.toByteArray()))).lines()
        .filter(s -> !s.isEmpty()).reduce((a, b) -> b).get().trim();
  }

  @Test
  public void test_badInput() throws IOException {
    String[] badInputStringsParsing = { "-", "'", "\n" };
    String[] badInputStringsValue = { "-1", "999" };

    for (int i = 0; i < badInputStringsParsing.length; i++) {
      AppStarter.printLineByInputNumber(badInputStringsParsing[i]);
      assertEquals(("Invalid line number: " + badInputStringsParsing[i]).trim(), getLatestOutputLine());
    }

    for (int i = 0; i < badInputStringsValue.length; i++) {
      AppStarter.printLineByInputNumber(badInputStringsValue[i]);
      assertEquals("Not found!", getLatestOutputLine());
    }
  }

  @Test
  public void test_xanaduFileConsistency() {
    AppStarter.printLineByInputNumber("0");
    assertEquals("In Xanadu did Kubla Khan", getLatestOutputLine());
    AppStarter.printLineByInputNumber("53");
    assertEquals("And drunk the milk of Paradise.", getLatestOutputLine());
  }

  @Test
  public void test_quitKey() {
    exit.expectSystemExit();
    AppStarter.printLineByInputNumber("q");
    assertEquals("Exit!", getLatestOutputLine());
  }

  @Test
  public void test_cache() throws Exception {
    int loadFromDiskCount = AppStarter.CACHE_SIZE == 0 ? 0 : 1;
    AppStarter.printLineByInputNumber("0");
    assertEquals(loadFromDiskCount, AppStarter.getCacheSize());
    AppStarter.printLineByInputNumber("1");
    AppStarter.printLineByInputNumber("2");
    AppStarter.printLineByInputNumber("1");
    AppStarter.printLineByInputNumber("2");
    AppStarter.printLineByInputNumber("2");
    assertEquals(Math.min(3, AppStarter.CACHE_SIZE), AppStarter.getCacheSize());
    loadFromDiskCount = AppStarter.CACHE_SIZE == 0 ? 6 : AppStarter.CACHE_SIZE == 1 ? 5 : 3;
    assertEquals(loadFromDiskCount, StringUtils.countMatches(outContent.toString(), "[Load from disk]"));
  }

}
