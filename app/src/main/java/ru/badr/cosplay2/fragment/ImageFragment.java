package ru.badr.cosplay2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 22.09.2015
 * 16:44
 */
public class ImageFragment extends Fragment {
    private String mImagePath;

    public void setImagePath(String path) {
        this.mImagePath = path;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_holder, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        Glide.with(this).load(mImagePath).into(imageView);

        return view;
    }

}
