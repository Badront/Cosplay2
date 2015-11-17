package ru.badr.cosplay2.util;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;
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

    public static boolean isTimeHasCome() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.DATE, 10);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.YEAR, 2015);
        return calendar.getTime().after(date);
    }

    public static String getSmallCardImageUrl(Context context, Card card) {
        return getCardImageUrl(context, card, "small");
    }
    public static String getCardImageUrl(Context context, Card card) {
        return getCardImageUrl(context, card, "large");
    }

    public static String getCardImageUrl(Context context, Card card, String size) {
        if (card == null) {
            return null;
        }
        Properties properties = Cosplay2BeanContainer.getInstance(context).getProperties();
        String baseUrl = properties.getProperty("global.url15");
        if (card instanceof ListCard && ((ListCard) card).getImage() != null) {
            CardImage cardImage = ((ListCard) card).getImage();
            String eventId = properties.getProperty("global.event_id15");
            return baseUrl + "uploads/" + eventId + "/" + card.getId() + "/" + cardImage.getFilename() + "_" + size + ".jpg";
        }
        return baseUrl + "images/avatars/" + card.getUserId() + ".png";
    }

    public static String getUserAvatar(Context context, User user) {
        Properties properties = Cosplay2BeanContainer.getInstance(context).getProperties();
        String baseUrl = properties.getProperty("global.url15");
        return baseUrl + "images/avatars/" + user.getId() + ".png";

    }
}
