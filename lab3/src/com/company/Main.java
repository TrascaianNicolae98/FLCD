package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Ui ui=new Ui("src/com/fa.txt");
        ui.run();
    }
}
