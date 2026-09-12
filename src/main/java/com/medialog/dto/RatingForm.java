package com.medialog.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RatingForm {

    @NotNull(message = "Choose a score from 1 to 10")
    @Min(value = 1, message = "The minimum score is 1")
    @Max(value = 10, message = "The maximum score is 10")
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
