package ru.badr.cosplay2.task;

import android.content.Context;
import android.text.TextUtils;

import com.octo.android.robospice.retry.DefaultRetryPolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.Cosplay2BeanContainer;
import ru.badr.cosplay2.api.media.Album;
import ru.badr.cosplay2.api.media.AlbumsAndPhotos;
import ru.badr.cosplay2.api.media.Photo;
import ru.badr.cosplay2.api.vk.VkAlbum;
import ru.badr.cosplay2.api.vk.VkAlbumsResponse;
import ru.badr.cosplay2.api.vk.VkPhoto;
import ru.badr.cosplay2.api.vk.VkPhotoResponse;
import ru.badr.cosplay2.api.vk.VkSize;
import ru.badr.cosplay2.remote.VkRestService;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 13:24
 */
public class AlbumsAndPhotosLoadRequest extends TaskRequest<AlbumsAndPhotos> {
    private Context mContext;

    public AlbumsAndPhotosLoadRequest(Context context) {
        super(AlbumsAndPhotos.class);
        this.mContext = context;
        setRetryPolicy(new DefaultRetryPolicy(1, DefaultRetryPolicy.DEFAULT_DELAY_BEFORE_RETRY, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public AlbumsAndPhotos loadData() throws Exception {
        Cosplay2BeanContainer cosplay2BeanContainer = Cosplay2BeanContainer.getInstance(mContext);
        AlbumsAndPhotos result = cosplay2BeanContainer.getCosplay2RestService().getAlbumsAndPhotos();

        if (result != null) {
            VkRestService vkService = cosplay2BeanContainer.getVkRestService();
            /*setting better thumbnails for photos through vk api, since default thumbnails are to small*/
            if (result.getPhotos() != null) {
                StringBuilder photoIds = new StringBuilder("");
                for (int i = 0, size = result.getPhotos().size(); i < size; i++) {
                    Photo photo = result.getPhotos().get(i);
                    if (i != 0) {
                        photoIds.append(",");
                    }
                    String[] parts = photo.getPhotoUrl().split("_");
                    String ownerId = parts[0].split("photo")[1];
                    String vkId = parts[1];
                    photo.setOwnerId(Long.valueOf(ownerId));
                    photo.setVkId(Long.valueOf(vkId));
                    photoIds.append(ownerId).append('_').append(vkId);
                }
                VkPhotoResponse vkPhotoResponse = vkService.getPhotos(photoIds.toString());
                if (vkPhotoResponse != null) {
                    for (Photo photo : result.getPhotos()) {
                        Iterator<VkPhoto> iterator = vkPhotoResponse.getPhotos().iterator();
                        while (iterator.hasNext()) {
                            VkPhoto vkPhoto = iterator.next();
                            if (vkPhoto.getId() == photo.getVkId()) {
                                photo.setSrc(vkPhoto.getLargeSrc());
                                photo.setLikes(vkPhoto.getLikes().getCount());
                                iterator.remove();
                                break;
                            }
                        }
                    }
                }
            }
            /*setting thumbnails for albums through vk api, since there's no default thumbnails for albums*/
            if (result.getAlbums() != null) {
                Map<Long, AlbumExtraHolder> ownersAlbums = new HashMap<>();
                for (Album album : result.getAlbums()) {
                    String[] parts = album.getUrl().split("_");
                    String ownerId = parts[0].split("album")[1];
                    String albumId = parts[1];
                    album.setOwnerId(Long.valueOf(ownerId));
                    album.setAlbumId(Long.valueOf(albumId));
                    if (ownersAlbums.containsKey(album.getOwnerId())) {
                        AlbumExtraHolder extraHolder =
                                ownersAlbums.get(album.getOwnerId());
                        extraHolder.albums.add(album);
                        extraHolder.albumsIds += "," + albumId;
                    } else {
                        AlbumExtraHolder extraHolder = new AlbumExtraHolder();
                        extraHolder.albums.add(album);
                        extraHolder.albumsIds = albumId;
                        ownersAlbums.put(album.getOwnerId(), extraHolder);
                    }
                }
                for (Long ownerId : ownersAlbums.keySet()) {
                    AlbumExtraHolder extraHolder = ownersAlbums.get(ownerId);
                    VkAlbumsResponse vkAlbumResponse = vkService.getAlbums(ownerId, extraHolder.albumsIds);
                    if (vkAlbumResponse != null && vkAlbumResponse.getAlbumHolder() != null) {
                        List<VkAlbum> vkAlbums = vkAlbumResponse.getAlbumHolder().getItems();
                        for (VkAlbum vkAlbum : vkAlbums) {
                            Iterator<Album> iterator = extraHolder.albums.iterator();
                            while ((iterator.hasNext())) {
                                Album album = iterator.next();
                                if (album.getAlbumId() == vkAlbum.getId()) {
                                    VkSize vkLast = null;
                                    for (VkSize vkSize : vkAlbum.getSizes()) {
                                        vkLast = vkSize;
                                        if ("x".equals(vkSize.getType())) {
                                            album.setThumbnailSrc(vkSize.getSrc());
                                            break;
                                        }
                                    }
                                    if (TextUtils.isEmpty(album.getThumbnailSrc()) && vkLast != null) {
                                        album.setThumbnailSrc(vkLast.getSrc());
                                    }
                                    iterator.remove();
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private class AlbumExtraHolder {
        List<Album> albums = new ArrayList<>();
        String albumsIds;
    }
}
