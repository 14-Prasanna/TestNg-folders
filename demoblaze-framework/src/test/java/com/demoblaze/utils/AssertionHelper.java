package com.demoblaze.utils;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AssertionHelper {

    private static final Logger log = LogManager.getLogger(AssertionHelper.class);

    private static final ThreadLocal<SoftAssert> softAssertThreadLocal = new ThreadLocal<>();

    public static void initSoftAssert() {
        softAssertThreadLocal.set(new SoftAssert());
        log.debug("SoftAssert initialized for thread: {}", Thread.currentThread().getName());
    }

    public static SoftAssert getSoftAssert() {
        if (softAssertThreadLocal.get() == null) {
            initSoftAssert();
        }
        return softAssertThreadLocal.get();
    }

    public static void assertAll() {
        SoftAssert sa = softAssertThreadLocal.get();
        if (sa != null) {
            log.info("Running assertAll() for soft assertions");
            sa.assertAll();
            softAssertThreadLocal.remove();
        }
    }

    @Step("Assert equals: expected='{expected}' actual='{actual}'")
    public static void assertEquals(Object actual, Object expected, String message) {
        log.info("Assert Equals | Expected: '{}' | Actual: '{}'", expected, actual);
        try {
            Assert.assertEquals(actual, expected, message);
            log.info("[PASS] assertEquals: '{}'", message);
        } catch (AssertionError e) {
            log.error("[FAIL] assertEquals: {} | Error: {}", message, e.getMessage());
            throw e;
        }
    }

    @Step("Assert true: condition='{condition}' message='{message}'")
    public static void assertTrue(boolean condition, String message) {
        log.info("Assert True | Message: '{}'", message);
        try {
            Assert.assertTrue(condition, message);
            log.info("[PASS] assertTrue: '{}'", message);
        } catch (AssertionError e) {
            log.error("[FAIL] assertTrue: {} | Error: {}", message, e.getMessage());
            throw e;
        }
    }

    @Step("Assert false: condition='{condition}' message='{message}'")
    public static void assertFalse(boolean condition, String message) {
        log.info("Assert False | Message: '{}'", message);
        try {
            Assert.assertFalse(condition, message);
            log.info("[PASS] assertFalse: '{}'", message);
        } catch (AssertionError e) {
            log.error("[FAIL] assertFalse: {} | Error: {}", message, e.getMessage());
            throw e;
        }
    }

    @Step("Assert not null: message='{message}'")
    public static void assertNotNull(Object object, String message) {
        log.info("Assert NotNull | Message: '{}'", message);
        try {
            Assert.assertNotNull(object, message);
            log.info("[PASS] assertNotNull: '{}'", message);
        } catch (AssertionError e) {
            log.error("[FAIL] assertNotNull: {}", message);
            throw e;
        }
    }

    @Step("Assert contains: text='{text}' should contain='{expected}'")
    public static void assertContains(String text, String expected, String message) {
        log.info("Assert Contains | Text: '{}' | Expected to contain: '{}'", text, expected);
        try {
            Assert.assertTrue(text != null && text.contains(expected), message);
            log.info("[PASS] assertContains: '{}'", message);
        } catch (AssertionError e) {
            log.error("[FAIL] assertContains: {} | '{}' does not contain '{}'", message, text, expected);
            throw e;
        }
    }

    @Step("Soft Assert equals: expected='{expected}' actual='{actual}'")
    public static void softAssertEquals(Object actual, Object expected, String message) {
        log.info("Soft Assert Equals | Expected: '{}' | Actual: '{}'", expected, actual);
        getSoftAssert().assertEquals(actual, expected, message);
    }

    @Step("Soft Assert true: message='{message}'")
    public static void softAssertTrue(boolean condition, String message) {
        log.info("Soft Assert True | Message: '{}'", message);
        getSoftAssert().assertTrue(condition, message);
    }

    @Step("Soft Assert contains: text='{text}' contains='{expected}'")
    public static void softAssertContains(String text, String expected, String message) {
        log.info("Soft Assert Contains | Text: '{}' | Expected: '{}'", text, expected);
        getSoftAssert().assertTrue(text != null && text.contains(expected), message);
    }
}
