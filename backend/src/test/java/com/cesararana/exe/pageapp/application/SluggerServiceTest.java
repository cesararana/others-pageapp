package com.cesararana.exe.pageapp.application;

import com.cesararana.exe.pageapp.application.impl.SluggerService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.cesararana.exe.pageapp.application.TestUtils.TestData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SluggerServiceTest {

    private static final List<TestData<String, String>> testData = List.of(
            td("", ""),
            td("Verify Every Hire", "verify-every-hire"),
            td("Aunt Millie's & Co., Inc.", "aunt-millies-and-co-inc"),
            td("Trusted By 99% of Skydivers!", "trusted-by-99-percent-of-skydivers"),
            td("Your local hitch-hiking experts", "your-local-hitch-hiking-experts"),
            td("  Your local hitch-hiking experts  ", "your-local-hitch-hiking-experts")
    );

    private static TestData<String, String> td(String value, String expected) {
        return new TestData<>(value, expected);
    }

    @Test
    void slugNonNullStrings() {

        SluggerService sluggerService = new SluggerService();
        testData.forEach(td -> {
            String result = sluggerService.slug(td.getValue());
            assertEquals(td.getExpected(), result);
        });

    }

    @Test
    void slugThrowsExceptionOnNull() {

        SluggerService sluggerService = new SluggerService();
        assertThrows(NullPointerException.class, () -> {
           sluggerService.slug(null);
        });
    }
}
