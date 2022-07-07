package com.ttce.vehiclemanage;

import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        int[] ary={2,4};
        List<Integer> list=new ArrayList<>();
        list.add(2);
        list.add(4);
        assertEquals(true, list.contains(2));
    }
}