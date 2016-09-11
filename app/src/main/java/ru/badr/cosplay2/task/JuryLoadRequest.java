package ru.badr.cosplay2.task;

import android.content.Context;

import com.google.gson.Gson;

import ru.badr.base.util.FileUtils;
import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.api.JurySectionEntity;

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
        return new Gson().fromJson(fileText, JurySectionEntity.List.class);
    }
}
