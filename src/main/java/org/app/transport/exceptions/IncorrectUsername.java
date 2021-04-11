package org.app.transport.exceptions;

public class IncorrectUsername extends Exception{
    public IncorrectUsername()
    {
        super("too few characters for username");
    }
}
