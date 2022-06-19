package com.example.hvorerduven;

//denne class er en singleton
public class LokalBruger extends User{

    private static LokalBruger lokalBruger;

    private String brugernavn = "Default";
    private String cardID;
    private String roomID;
    private Room currentRoom = new Room();
    private boolean isTheUserLoggedIn = false;


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

    @Override
    public void setBrugernavn(String brugernavn) {
        if (lokalBruger == null) {
            lokalBruger = new LokalBruger();
        }

        System.out.println("lokalbruger modtager: " + brugernavn);
        lokalBruger.brugernavn = brugernavn;
    }

    @Override
    public String getBrugernavn() {
        if (lokalBruger == null) {
            lokalBruger = new LokalBruger();
        }

        return lokalBruger.brugernavn;
    }

    public void setIsTheUserLoggedIn(boolean isTheUserLoggedIn) {
        lokalBruger.isTheUserLoggedIn = isTheUserLoggedIn;
    }
}
