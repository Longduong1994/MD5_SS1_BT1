package ra.model.service;

import ra.model.dto.FormSongDto;
import ra.model.entity.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    Song findByID(Long id);
    void save(FormSongDto s);
    void delete(Long id);
}
