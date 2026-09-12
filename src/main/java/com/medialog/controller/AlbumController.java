package com.medialog.controller;

import com.medialog.dto.RatingForm;
import com.medialog.entity.Rating;
import com.medialog.service.AlbumService;
import com.medialog.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final RatingService ratingService;

    public AlbumController(AlbumService albumService, RatingService ratingService) {
        this.albumService = albumService;
        this.ratingService = ratingService;
    }

    @GetMapping
    public String listAlbums(Model model) {
        model.addAttribute("albums", albumService.findAllAlbums());
        return "albums/list";
    }

    @GetMapping("/{id}")
    public String albumDetails(@PathVariable Long id, Model model) {
        RatingForm ratingForm = new RatingForm();
        ratingService.findDemoUserRating(id)
                .map(Rating::getScore)
                .ifPresent(ratingForm::setScore);
        model.addAttribute("ratingForm", ratingForm);
        addAlbumPageData(id, model);
        return "albums/detail";
    }

    @PostMapping("/{id}/ratings")
    public String saveRating(@PathVariable Long id,
                             @Valid @ModelAttribute RatingForm ratingForm,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addAlbumPageData(id, model);
            return "albums/detail";
        }

        ratingService.saveDemoUserRating(id, ratingForm.getScore());
        redirectAttributes.addFlashAttribute("message", "Your rating was saved.");
        return "redirect:/albums/" + id;
    }

    @PostMapping("/{id}/ratings/delete")
    public String deleteRating(@PathVariable Long id,
                               RedirectAttributes redirectAttributes) {
        ratingService.deleteDemoUserRating(id);
        redirectAttributes.addFlashAttribute("message", "Your rating was removed.");
        return "redirect:/albums/" + id;
    }

    private void addAlbumPageData(Long id, Model model) {
        model.addAttribute("album", albumService.findAlbumById(id));
        model.addAttribute("currentRating", ratingService.findDemoUserRating(id).orElse(null));
        model.addAttribute("averageRating", ratingService.findAverageScore(id));
    }
}
