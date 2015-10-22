package ru.badr.cosplay2.remote;

import retrofit.http.GET;
import retrofit.http.Query;
import ru.badr.cosplay2.api.vk.VkAlbumsResponse;
import ru.badr.cosplay2.api.vk.VkPhotoResponse;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 14:11
 */
public interface VkRestService {
    /*т.к. нам нужны только обложки альбомом, не будем заморачиваться лишними параметрами*/
    @GET("/photos.getAlbums?need_covers=1&photo_sizes=1&v=5.37")
    VkAlbumsResponse getAlbums(@Query("owner_id") long ownerId, @Query("album_ids") String albumIds);

    @GET("/photos.getById?v=5.37&extended=1")
    VkPhotoResponse getPhotos(@Query("photos") String photoIds);
}
