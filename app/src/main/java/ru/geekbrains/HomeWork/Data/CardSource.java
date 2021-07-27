package ru.geekbrains.HomeWork.Data;

public interface CardSource {
    int size ();
    CardData getCardData (int position);

    void addCardData (CardData cardData);
    void deleteCardData  (int position);
    void updateCardData (int position,CardData cardData);
    void clearCardData();
}
