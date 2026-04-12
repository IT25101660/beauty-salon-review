package com.example.beautysalonreview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewWebController {

    private ReviewController reviewController = new ReviewController();

    @GetMapping("/")
    public String showHomePage() {
        return "review-form";
    }

    @PostMapping("/submitReview")
    public String submitReview(
            @RequestParam int reviewId,
            @RequestParam String customerName,
            @RequestParam String serviceName,
            @RequestParam int rating,
            @RequestParam String comment
    ) {
        Review review = new Review(reviewId, customerName, serviceName, rating, comment);
        reviewController.addReview(review);
        return "redirect:/";
    }

    @GetMapping("/reviews")
    public String showReviewsPage(Model model) {
        model.addAttribute("reviews", reviewController.getAllReviews());
        return "review-list";
    }
}