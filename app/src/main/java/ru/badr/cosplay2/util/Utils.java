package ru.badr.cosplay2.util;

import android.content.Context;

import java.util.Properties;

import ru.badr.cosplay2.Cosplay2BeanContainer;
import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.api.cards.CardImage;
import ru.badr.cosplay2.api.cards.User;
import ru.badr.cosplay2.api.cards.list.ListCard;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 16:33
 */
public class Utils {
    public static String getCardImageUrl(Context context, Card card) {
        Properties properties = Cosplay2BeanContainer.getInstance(context).getProperties();
        String baseUrl = properties.getProperty("global.url15");
        if (card instanceof ListCard && ((ListCard) card).getImage() != null) {
            CardImage cardImage = ((ListCard) card).getImage();
            String eventId = properties.getProperty("global.event_id15");
            return baseUrl + "uploads/" + eventId + "/" + card.getId() + "/" + cardImage.getFilename() + "_large.jpg";
        }
        return baseUrl + "images/avatars/" + card.getUserId() + ".png";
    }

    public static String getUserAvatar(Context context, User user) {
        Properties properties = Cosplay2BeanContainer.getInstance(context).getProperties();
        String baseUrl = properties.getProperty("global.url15");
        return baseUrl + "images/avatars/" + user.getId() + ".png";

    }
}
