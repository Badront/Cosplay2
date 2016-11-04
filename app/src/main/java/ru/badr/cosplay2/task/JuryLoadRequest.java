package ru.badr.cosplay2.task;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.Iterator;

import ru.badr.base.util.FileUtils;
import ru.badr.base.util.json.DateLongSerializer;
import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.api.JuryEntity;
import ru.badr.cosplay2.api.JurySectionEntity;
import ru.badr.cosplay2.util.Utils;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 18:10
 */
public class JuryLoadRequest extends TaskRequest<JurySectionEntity.List> {
    private Context mContext;

    public JuryLoadRequest(Context context) {
        super(JurySectionEntity.List.class);
        this.mContext = context.getApplicationContext();
    }

    @Override
    public JurySectionEntity.List loadData() throws Exception {
        String fileText = FileUtils.getTextFromAsset(mContext, "jury.json");
        JurySectionEntity.List result = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongSerializer())
                .create()
                .fromJson(fileText, JurySectionEntity.List.class);
        Iterator<JurySectionEntity> sectionIterator = result.iterator();
        while (sectionIterator.hasNext()) {
            JurySectionEntity section = sectionIterator.next();
            Iterator<JuryEntity> iterator = section.getList().iterator();
            while (iterator.hasNext()) {
                JuryEntity entity = iterator.next();
                if (entity.getShowUpDate().after(Utils.APP_START_TIME)) {
                    iterator.remove();
                }
            }
            if (section.getList().isEmpty()) {
                sectionIterator.remove();
            }
        }
        //NotificationUtils.showNotification(mContext,result.get(0).getList().get(0));
        return result;
    }
}
