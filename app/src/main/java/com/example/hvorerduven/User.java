package com.example.hvorerduven;

public class User {
    private String brugernavn;
    private String password;
    private String cardID;
    private String roomID;
    //måske de her skal være public?

    private Room currentRoom = new Room();
    private Card currentCard;

    public User(){

    }

    public User(String brugernavn) {
        this.brugernavn = brugernavn;

    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }
}
