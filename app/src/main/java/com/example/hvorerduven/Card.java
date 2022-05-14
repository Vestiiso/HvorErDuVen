package com.example.hvorerduven;

import java.util.ArrayList;

public class Card {

    private int cardID;
    private String cardName;
    private ArrayList<User> usersInCard = new ArrayList<>();
    private ArrayList<String> userNamesInCard = new ArrayList<>();
    private Room belongsToRoom;

    public Card(String cardName) {
        this.cardName = cardName;

        //her skal vi tilføje navnene fra vores database, men jeg tilføjer dem bare manuelt for at teste
        usersInCard.add(new User("Simba"));
        usersInCard.add(new User("Nala"));
        usersInCard.add(new User("Timon"));
        usersInCard.add(new User("Pumbaa"));
        usersInCard.add(new User("Scar"));
        usersInCard.add(new User("Mufasa"));

        for (int i = 0; i < usersInCard.size(); i++ ){
            userNamesInCard.add(usersInCard.get(i).getBrugernavn() );
            //System.out.println(usersInCard.get(i).getBrugernavn());
        }

    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public ArrayList<User> getUsersInCard() {
        return usersInCard;
    }

    public String getUserNamesInCard() {
        //System.out.println(userNamesInCard.toString());
        return userNamesInCard.toString();
    }

    public void setUsersInCard(ArrayList<User> usersInCard) {
        this.usersInCard = usersInCard;
    }

    public Room getBelongsToRoom() {
        return belongsToRoom;
    }

    public void setBelongsToRoom(Room belongsToRoom) {
        this.belongsToRoom = belongsToRoom;
    }
}
