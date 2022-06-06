package hr.tvz.pilizota.hardwareapp.web;

import hr.tvz.pilizota.hardwareapp.review.ReviewDTO;
import hr.tvz.pilizota.hardwareapp.review.ReviewService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public List<ReviewDTO> getAllReviews(){
        return reviewService.findAll();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(params = "code")
    public List<ReviewDTO> getAllReviewsByHardwareCode(@RequestParam String code){
        return reviewService.findAllByHardwareCode(code);
    }
}
