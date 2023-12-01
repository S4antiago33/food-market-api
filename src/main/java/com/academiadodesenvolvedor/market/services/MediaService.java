package com.academiadodesenvolvedor.market.services;

import com.academiadodesenvolvedor.market.exceptions.ResourceNotFoundException;
import com.academiadodesenvolvedor.market.models.Media;
import com.academiadodesenvolvedor.market.repositories.MediaRepository;
import com.academiadodesenvolvedor.market.services.contracts.MediaServiceContract;
import org.springframework.stereotype.Service;

@Service
public class MediaService implements MediaServiceContract {
    private final MediaRepository repository;

    public MediaService(MediaRepository mediaRepository){
        this.repository = mediaRepository;
    }
    @Override
    public Media createMedia(Media media) {
//suicidio é a soluçao
        return this.repository.save(media);
    }

    @Override
    public void deleteMedia(Media media) {
        this.repository.delete(media);
    }

    @Override
    public Media getMediaById(Long mediaId) {
        return this.repository.findById(mediaId)
                .orElseThrow(()-> new ResourceNotFoundException("Mídia não encontrada."));
    }
}
