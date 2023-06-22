package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardRepository boardRepo;

	@Override
	public List<Board> getBoardList(){//게시판 목록
		return boardRepo.findAll();
	}
	
	@Override
	public void insertBoard(Board board) {//새 글 등록
		boardRepo.save(board);
	}
	
	@Override
	public Board getBoard(Board board) {//상세조회
		Board b = boardRepo.findById(board.getSeq()).get();
		b.setCnt(b.getCnt()+1); //조회 할때마다 조회수 1 증가
		return boardRepo.save(b);
	}
	
	@Override
	public void updateBoard(Board board) {//글 수정
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}
	
	@Override
	public void deleteBoard(Board board) {//글 삭제
		boardRepo.deleteById(board.getSeq());
	}
}
