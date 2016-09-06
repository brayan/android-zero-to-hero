package com.brayanbedritchuk.zerotohero;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestzeraPower {

    @Mock
    Context context;

    @Test
    public void testString() {
        when(context.getString(R.string.app_name)).thenReturn("Zerooo");
        assertEquals(context.getString(R.string.app_name), "Zerooo");
    }

}
