package com.bharath.exercise.zipcode;

public class RangeException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public RangeException(String message)
    {
        super(message);
    }

    public RangeException(String message, Throwable exception)
    {
        super(message, exception);
    }

    public RangeException(Throwable exception)
    {
        super(exception);
    }
}
