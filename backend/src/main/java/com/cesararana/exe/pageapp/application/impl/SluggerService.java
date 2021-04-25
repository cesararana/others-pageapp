package com.cesararana.exe.pageapp.application.impl;

import com.cesararana.exe.pageapp.application.Slugger;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SluggerService implements Slugger {

    private static final Pattern FIRST_RE = Pattern.compile("(\\s+)|([^\\s])", Pattern.CASE_INSENSITIVE);

    /**
     * Slug a string
     * <p>
     * If null is given NullPointerException is thrown
     * <p>
     * Method is not optimized for long strings.
     *
     * @param value to slug
     * @return slugged value
     */
    @Override
    public String slug(String value) {

        if (value == null) {
            throw new NullPointerException("value can't be null");
        }

        Matcher matcher = FIRST_RE.matcher(value);
        return matcher.replaceAll(m -> {

            // first replace chars given rules

            String group = m.group(0);
            char head = group.charAt(0);
            if (Character.isSpaceChar(head)) {
                return "-";
            }
            switch (head) {
                case '%':
                    return "-percent-";
                case '&':
                    return "-and-";
                case '@':
                    return "-at-";
                case '-':
                    return "-";
            }
            if (!Character.isDigit(head) && !Character.isAlphabetic(head)) {
                return "";
            }
            return group.toLowerCase();
        })
                // fix final string given rules
                .replaceAll("\\-+", "-")
                // no - at begin or end
                .replaceAll("^\\-+|\\-+$", "");
    }

}
