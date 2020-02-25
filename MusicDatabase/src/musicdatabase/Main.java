/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicdatabase;

import gui.MainJFrame;

/**
 *
 * @author Daniel Lindkvist
 */
public class Main {

    public static void main(String[] args) {
        DBManager dbm = new DBManager();
        MainJFrame frame = new MainJFrame(dbm);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

}
