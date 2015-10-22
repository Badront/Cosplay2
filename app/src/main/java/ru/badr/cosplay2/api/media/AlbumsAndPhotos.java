package ru.badr.cosplay2.api.media;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 12:59
 */
public class AlbumsAndPhotos implements Serializable {
    private List<Album> albums;
    private List<Photo> photos;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
