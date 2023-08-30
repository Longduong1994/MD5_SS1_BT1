package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.model.dto.FormSongDto;
import ra.model.service.ISongService;

@Controller
public class SongController {
    @Autowired
    private ISongService songService;
    @GetMapping
    public String home(Model model) {
        model.addAttribute("songs",songService.findAll());
        return "home";
    }
    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("add","song",new FormSongDto());
    }
    @PostMapping("/add")
    public String doAdd(@ModelAttribute FormSongDto song){
        songService.save(song);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editSong(@PathVariable Long id){
        return new ModelAndView("edit","song",songService.findByID(id));
    }
    @PostMapping("update")
    public String doUpdate(@ModelAttribute FormSongDto song){
        songService.save(song);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        songService.delete(id);
        return "redirect:/";
    }
}
