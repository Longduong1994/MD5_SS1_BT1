package ra.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String singer;
    private String category;
    private String file_url;

    public Song() {
    }

    public Song(Long id, String name, String singer, String category, String file_url) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.category = category;
        this.file_url = file_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }


    public void clone(Song s) {
        this.id = s.getId();
        this.name = s.getName();
        this.singer = s.getSinger();
        this.category = s.getCategory();
        this.file_url =s.getFile_url();
    }
}
