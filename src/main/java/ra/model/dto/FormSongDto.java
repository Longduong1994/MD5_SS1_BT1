package ra.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class FormSongDto {
    private Long id;
    private String name;
    private String singer;
    private String category;
    private MultipartFile file_url;

    public FormSongDto() {
    }

    public FormSongDto(Long id, String name, String singer, String category, MultipartFile file_url) {
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

    public MultipartFile getFile_url() {
        return file_url;
    }

    public void setFile_url(MultipartFile file_url) {
        this.file_url = file_url;
    }
}
