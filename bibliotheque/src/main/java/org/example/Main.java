package org.example;

import org.example.Menu.MainMenu;
import org.example.Repositories.AuteurRepository;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import static java.time.InstantSource.system;

public class Main {
    public static void main(String[] args) throws Exception {
        MainMenu.menu();
    }
}

