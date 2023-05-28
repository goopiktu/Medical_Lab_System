package MainMenu;

import java.util.Scanner;

public class Terminal {
    public Scanner sc = new Scanner(System.in);
    public boolean found;
    public int searchRetry;
    public int searchError;

    public void scannerClose() {
        sc.close();
    }
}
