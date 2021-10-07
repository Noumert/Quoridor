package controller;

import view.View;

public class MainController {

    public void startGame(){
        View view = new View();

        view.printMessage(View.Greet);
    }
}
