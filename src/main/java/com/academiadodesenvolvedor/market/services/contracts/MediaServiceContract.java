package com.academiadodesenvolvedor.market.services.contracts;


import com.academiadodesenvolvedor.market.models.Media;

public interface MediaServiceContract {
    Media createMedia(Media media);

    void deleteMedia(Media media);

    Media getMediaById(Long mediaId);
}
