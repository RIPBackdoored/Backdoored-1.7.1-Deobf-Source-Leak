package com.google.api.client.extensions.java6.auth.oauth2;

import java.util.Scanner;

public abstract class AbstractPromptReceiver implements VerificationCodeReceiver
{
    public AbstractPromptReceiver() {
        super();
    }
    
    public String waitForCode() {
        String nextLine;
        do {
            System.out.print("Please enter code: ");
            nextLine = new Scanner(System.in).nextLine();
        } while (nextLine.isEmpty());
        return nextLine;
    }
    
    public void stop() {
    }
}
