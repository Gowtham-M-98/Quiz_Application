package com.abc.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abc.quizapp.dao.QuestionDao;
import com.abc.quizapp.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questionDao.save(question);
		try {
			return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Question not inserted", HttpStatus.NOT_ACCEPTABLE);
	}
	
	public ResponseEntity<Object> updateQuestionById(int id, Question question) {
		try {
			Optional<Question> questionOpt = questionDao.findById(id);
			 if (questionOpt.isPresent()) {
		            Question existingQuestion = questionOpt.get();
		            existingQuestion.setQuestionTitle(question.getQuestionTitle());
		            existingQuestion.setOption1(question.getOption1());
		            existingQuestion.setOption2(question.getOption2());
		            existingQuestion.setOption3(question.getOption3());
		            existingQuestion.setOption4(question.getOption4());
		            existingQuestion.setRightAnswer(question.getRightAnswer());
		            existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
		            existingQuestion.setCategory(question.getCategory());

		            questionDao.save(existingQuestion);
		            return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return new ResponseEntity<>("An error occurred while updating the question", HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

	public ResponseEntity<String> deleteQuestionById(int id) {
		questionDao.deleteById(id);
		try {
		return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Question not found",HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<String> addAllQuestion(List<Question> questions) {
		questionDao.saveAll(questions);
		try {
			return new ResponseEntity<>("All questions added successfully", HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Questions not inserted", HttpStatus.NOT_ACCEPTABLE);
	}
}
