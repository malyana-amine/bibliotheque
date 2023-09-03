package sq.src;

import org.example.Menu.MainMenu;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import static java.time.InstantSource.system;

public class Main {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();

        menu.menu();

    }
}
