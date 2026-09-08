package com.travel.service;

import com.travel.dto.QuestionDTO;
import com.travel.dto.ReplyDTO;
import com.travel.entity.Destination;
import com.travel.entity.Question;
import com.travel.entity.Reply;
import com.travel.repository.QuestionRepository;
import com.travel.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository questionRepo;
    private final ReplyRepository replyRepo;

    public QuestionService(QuestionRepository questionRepo, ReplyRepository replyRepo) {
        this.questionRepo = questionRepo;
        this.replyRepo = replyRepo;
    }

    public List<QuestionDTO> getQuestionsByDestination(Long destId) {
        return questionRepo.findByDestinationIdOrderByCreatedAtDesc(destId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<QuestionDTO> getQuestionsByStatus(String status) {
        return questionRepo.findByStatus(status)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<QuestionDTO> getAllQuestions() {
    return questionRepository.findAll()
            .stream()
            .map(QuestionMapper::toDTO)
            .toList();
    }


    public QuestionDTO addQuestion(Long destId, Question q) {
        Destination dest = new Destination();
        dest.setId(destId);
        q.setDestination(dest);
        Question saved = questionRepo.save(q);
        return mapToDTO(saved);
    }

    public ReplyDTO addReply(Long questionId, Reply reply) {
        Question q = questionRepo.findById(questionId).orElseThrow();
        reply.setQuestion(q);
        Reply saved = replyRepo.save(reply);
        q.setStatus("answered");
        questionRepo.save(q);
        return mapReplyToDTO(saved);
    }

    private QuestionDTO mapToDTO(Question q) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(q.getId());
        dto.setName(q.getName());
        dto.setEmail(q.getEmail());
        dto.setAvatarUrl(q.getAvatarUrl());
        dto.setContent(q.getContent());
        dto.setStatus(q.getStatus());
        dto.setCreatedAt(q.getCreatedAt());
        dto.setReplies(replyRepo.findByQuestionIdOrderByCreatedAtAsc(q.getId())
                .stream().map(this::mapReplyToDTO).collect(Collectors.toList()));
        return dto;
    }

    private ReplyDTO mapReplyToDTO(Reply r) {
        ReplyDTO dto = new ReplyDTO();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setEmail(r.getEmail());
        dto.setAvatarUrl(r.getAvatarUrl());
        dto.setContent(r.getContent());
        dto.setCreatedAt(r.getCreatedAt());
        return dto;
    }
}
