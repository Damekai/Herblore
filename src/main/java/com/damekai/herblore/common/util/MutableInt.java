package com.damekai.herblore.common.util;

/**
 * A nullable Mutable Integer class to optimize Map value updates and prevent NullPointerExceptions from Map unboxings.
 */
public class MutableInt
{
    private int value;

    public MutableInt(int value)
    {
        this.value = value;
    }

    public void set(int newValue)
    {
        value = newValue;
    }

    public int get()
    {
        return value;
    }

    public void increment()
    {
        value++;
    }

    public void decrement()
    {
        value--;
    }
}