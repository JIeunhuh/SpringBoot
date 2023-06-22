package edu.pnu.persistence;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	// 1. title에 1 포함되는 데이터 출력
	public List<Board> findByTitleLike(String title);

	// 2. title에 "1"이 포함되면서 cnt가 50보다 큰 데이터
	public List<Board> findByTitleLikeAndCntGreaterThan(String title, Long cnt);

	// 3. cnt가 10 - 50 사이인 데이터를 seq 오름차순으로 출력
	public List<Board> findByCntBetweenOrderBySeqAsc(Long cnt1, Long cnt2);

	// 4. title에 "10"이 포함되거나 content에 "2"가 포함되는 데이터를 seq 내림차순
	public List<Board> findByTitleLikeOrContentLikeOrderBySeqDesc(String title, String content);

	// error ; Pageable import 잘 시켜야함! 상위에 있는 pw뭐시기 하면 안되고 springboot.뭐시기.domain 돼있는거
	// paging
	public Page<Board> findAll(Pageable page);

	// 위치 기반 파라미터 사용(preparedstatement랑 비슷함)
	@Query("select b from Board b where b.title like %?1% order by b.seq desc")
	List<Board> queryAnnotationTest1(String searchKeyword);

	// 이름 기반 파라미터 사용
	@Query("select b from Board b where b.title like %:searchKeyword% order by b.seq desc")
	List<Board> queryAnnotationTest2(String searchKeyword);

	// 특정 변수만 조회
	@Query("select b.seq, b.title, b.writer, b.createDate from Board b where b.title like %:skw% order by b.seq desc")
	List<Object[]> queryAnnotationTest3(@Param("skw") String searchKeyword); //@Param() 어노테이션은 query문과 변수이름이 매핑되지 않을때 사용
	
	// Native Query -> DB에서 카멜표기법(hiHi)을 자동으로 스네이크표기법(hi_hihi)으로 바꿔주므로 쿼리문 쓸때 언더바 붙여줌 
	@Query(value="select seq, title, writer, content, create_date from board where title like '%'||?1||'%' order by seq desc", nativeQuery = true)
	List<Object[]> queryAnnotationTest4(String searchKeyword);
	
	// 페이징 및 정렬 처리
	@Query("select b from Board b order by b.seq asc")
	List<Board> queryAnnotationTest5(Pageable paging);
}
