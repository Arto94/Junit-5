package am.itspace.junit;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

import java.time.Month;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@RunWith(JUnitPlatform.class)
@SelectPackages("am.itspace")
public class JunitApplicationTests {


    // instead of @BeforeClass
    @BeforeAll
    static void initAll() {

    }

    // instead of @Before
    @BeforeEach
    void init() {
        System.out.println("Before Each Test");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = {"DAYS", "HOURS"})
    void testWithEnumSourceInclude(TimeUnit timeUnit) {
        assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
    }


    @ParameterizedTest
    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
    public void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }


    @ParameterizedTest(name = "{index} {0} is 30 days long")
    @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    void someMonths_Are30DaysLong(Month month) {
        final boolean isALeapYear = false;
        assertEquals(30, month.length(isALeapYear));
    }

    @Test
    public void succeedingTest() {

    }


    // instead of @Ignore
    @Test
    @Disabled("for demonstration purposes")
    public void skippedTest() {
        // not executed
    }


    @DisplayName("emo")
    public void testWithDisplayNameContainingSpecialCharacters() {

    }

    // instead of @After
    @AfterEach
    public void tearDown() {
        System.out.println("After Each Test");
        System.out.println("=====================");
    }

    // instead of @AfterClass
    @AfterAll
    static void tearDownAll() {

    }


    @RepeatedTest(3)
    void repeatedTest() {
        System.out.println("Executing repeated test");

        assertEquals(2, Math.addExact(1, 1), "1 + 1 should equal 2");
    }


    @Test
    public void myFirstTest() {
        String message = "1+1 should be equal to 2";
        System.out.println(message);

        assertEquals(2, 1 + 1, message);
    }


}
