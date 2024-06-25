//VepAbstract.java
package org.example;

import java.util.Scanner;

public abstract class VepAbstract implements Permit {
    final String url = "jdbc:mysql://localhost:3306/tbpsns";
    final String username = "root";
    final String password = "";
    public Scanner scanner = new Scanner(System.in);

    public VepAbstract() {
        // Initialize scanner
    }

    public abstract void read();
}
