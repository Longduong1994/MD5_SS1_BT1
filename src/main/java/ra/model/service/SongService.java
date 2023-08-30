package ra.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ra.model.dto.FormSongDto;
import ra.model.entity.Song;
import ra.model.repository.ISongRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public class SongService implements ISongService{
    private String pathUrl = "C:\\Users\\Admin\\IdeaProjects\\ListenToMuzik\\src\\main\\webapp\\WEB-INF\\upload\\";
    @Autowired
    private ISongRepository songRepository;

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Song findByID(Long id) {
        return songRepository.findByID(id);
    }

    @Override
    public void save(FormSongDto s) {
        String filename = null;
        if(!(s.getFile_url().isEmpty())){
            filename = s.getFile_url().getOriginalFilename();
            try {
                FileCopyUtils.copy(s.getFile_url().getBytes(),new File(pathUrl+filename));
            }catch (IOException e){
                throw  new RuntimeException(e);
            }
        }
        Song song = new Song(s.getId()
                ,s.getName(),s.getSinger(),s.getCategory()
                ,filename);
        songRepository.save(song);
    }

    @Override
    public void delete(Long id) {
        songRepository.delete(id);
    }
}
