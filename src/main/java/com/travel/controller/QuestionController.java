package com.travel.controller;

import com.travel.dto.QuestionDTO;
import com.travel.dto.ReplyDTO;
import com.travel.entity.Question;
import com.travel.entity.Reply;
import com.travel.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
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

    // Xóa question theo id (sẽ xóa luôn replies liên quan nhờ cascade)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    // Xóa reply theo id
    @DeleteMapping("/reply/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long id) {
        questionService.deleteReply(id);
        return ResponseEntity.noContent().build();
    }
}
