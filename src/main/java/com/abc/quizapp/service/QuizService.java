package com.abc.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abc.quizapp.dao.QuestionDao;
import com.abc.quizapp.dao.QuizDao;
import com.abc.quizapp.model.Question;
import com.abc.quizapp.model.QuestionWrapper;
import com.abc.quizapp.model.Quiz;
import com.abc.quizapp.model.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Quiz was successfully created", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question> qFromDB= quiz.get().getQuestions();
		List<QuestionWrapper> qForUser = new ArrayList<>();
		for(Question q : qFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			qForUser.add(qw);
		}
		return new ResponseEntity<>(qForUser, HttpStatus.OK); 
}

	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
		 Quiz quiz = quizDao.findById(id).get();
		 List<Question> questions = quiz.getQuestions();
		 int right = 0;
		 int i = 0;
		 for(Response response : responses) {
			 if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
				 right++;
			 }
			 i++;
		 }
		 return new ResponseEntity<>(right, HttpStatus.OK);
	}
}
