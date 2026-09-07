package com.travel.controller;

import com.travel.dto.QuestionDTO;
import com.travel.dto.ReplyDTO;
import com.travel.entity.Question;
import com.travel.entity.Reply;
import com.travel.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Lấy danh sách question theo destination
    @GetMapping("/destination/{destId}")
    public List<QuestionDTO> getQuestions(@PathVariable Long destId) {
        return questionService.getQuestionsByDestination(destId);
    }

    // Lấy danh sách question theo status (pending/answered)
    @GetMapping("/status/{status}")
    public List<QuestionDTO> getQuestionsByStatus(@PathVariable String status) {
        return questionService.getQuestionsByStatus(status);
    }

    // Thêm question mới cho destination
    @PostMapping("/destination/{destId}")
    public QuestionDTO addQuestion(@PathVariable Long destId, @RequestBody Question question) {
        return questionService.addQuestion(destId, question);
    }

    @PostMapping("/{questionId}/reply")
    public ReplyDTO addReply(@PathVariable Long questionId, @RequestBody Reply reply) {
        return questionService.addReply(questionId, reply);
    }
}
