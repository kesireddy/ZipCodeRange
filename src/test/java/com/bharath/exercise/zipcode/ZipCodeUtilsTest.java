package com.bharath.exercise.zipcode;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;

public class ZipCodeUtilsTest {
    @Test
    public void testEmptyRange() {
        List<Range> ranges = Collections.emptyList();
        List<Range> expected = Collections.emptyList();
        ZipCodeUtils zipCodeUtils = new ZipCodeUtils();
        List<Range> result = zipCodeUtils.getCompressedRange(ranges);
        assertEquals(expected, result);
    }

    @Test
    public void testOverlappingRanges() {
        List<Range> ranges = Arrays.asList(new Range(94133,94133), new Range(94200,94299), new Range(94226,94399));
        Collections.shuffle(ranges);
        List<Range> expected = Arrays.asList(new Range(94133,94133), new Range(94200,94399));
        ZipCodeUtils zipCodeUtils = new ZipCodeUtils();
        List<Range> result = zipCodeUtils.getCompressedRange(ranges);
        assertEquals(expected, result);
    }

    @Test
    public void testDistinctRanges() {
        List<Range> ranges = Arrays.asList(new Range(94133,94133), new Range(94200,94299), new Range(94600,94699));
        Collections.shuffle(ranges);
        List<Range> expected = Arrays.asList(new Range(94133,94133), new Range(94200,94299), new Range(94600,94699));
        ZipCodeUtils zipCodeUtils = new ZipCodeUtils();
        List<Range> result = zipCodeUtils.getCompressedRange(ranges);
        assertEquals(expected, result);
    }

    @Test
    public void testComboRanges() {
        List<Range> ranges = Arrays.asList(new Range(1,100), new Range(102,200), new Range(1,100), new Range(150,250));
        Collections.shuffle(ranges);
        List<Range> expected = Arrays.asList(new Range(1,100), new Range(102,250));
        ZipCodeUtils zipCodeUtils = new ZipCodeUtils();
        List<Range> result = zipCodeUtils.getCompressedRange(ranges);
        System.out.println(zipCodeUtils.getCompressedRange(ranges));
        assertEquals(expected, result);
    }

    @Test(expected=RangeException.class)
    public void testIncorrectRange() {
        List<Range> ranges = Arrays.asList(new Range(1,100), new Range(102,200), new Range(250,150));
        Collections.shuffle(ranges);
        ZipCodeUtils zipCodeUtils = new ZipCodeUtils();
        List<Range> result = zipCodeUtils.getCompressedRange(ranges);
    }

}