package com.example.hvorerduven;

//denne class er en singleton
public class LokalBruger extends User{

    private static LokalBruger lokalBruger;

    private String brugernavn = "Nickolai";
    private String password;
    private Boolean erLoggetInd;
    private String cardID;
    private String roomID;
    private Room currentRoom = new Room();


    private LokalBruger() {

    }

    public static LokalBruger getInstance() {

        if (lokalBruger == null) {
            lokalBruger = new LokalBruger();
        }
        return lokalBruger;

    }

    public String getLokalNavn () {
        if (lokalBruger == null) {
            lokalBruger = new LokalBruger();
        }
        return lokalBruger.brugernavn;
    }

}
