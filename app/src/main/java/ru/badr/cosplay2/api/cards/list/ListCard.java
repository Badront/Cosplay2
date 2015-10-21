package ru.badr.cosplay2.api.cards.list;

import java.util.ArrayList;

import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.api.cards.CardImage;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 12:43
 */
public class ListCard extends Card {
    private CardImage image;

    public ListCard() {
        super();
    }

    public ListCard(ListCard card) {
        super(card);
        this.image = card.image != null ? new CardImage(card.image) : null;
    }

    public CardImage getImage() {
        return image;
    }

    public void setImage(CardImage image) {
        this.image = image;
    }

    public static class List extends ArrayList<ListCard> {
    }
}
