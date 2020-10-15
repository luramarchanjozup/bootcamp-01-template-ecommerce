package com.zup.mercadolivre.controller.dto;

import java.util.List;

public class QuestionListDto {
    
    private List<QuestionDto> questions;

    public QuestionListDto(List<QuestionDto> questions) {
        this.questions = questions;
    }

    public List<QuestionDto> getQuestions() {
        return this.questions;
    }

}
