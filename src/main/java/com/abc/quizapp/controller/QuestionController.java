package com.abc.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.quizapp.model.Question;
import com.abc.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("allquestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions() ;
	}
	
	@GetMapping("category/{lang}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("lang") String category){
		return questionService.getQuestionByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		 return questionService.addQuestion(question);
	}
	
	@PostMapping("addAll")
	public ResponseEntity<String> addAllTheQuestion(@RequestBody List<Question> questions) {
		 return questionService.addAllQuestion(questions);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Object> updateQuestionById(@PathVariable int id,@RequestBody Question question){
		return questionService.updateQuestionById(id, question);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteQuestionById(@PathVariable int id) {
		return questionService.deleteQuestionById(id);
	}
}
