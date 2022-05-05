package kr.datasolution.msa.frontend.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.datasolution.msa.frontend.board.dto.BoardDto;
import kr.datasolution.msa.frontend.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시물 관련 처리 Controller Layer
 */
@RestController
@RequestMapping("/boardms")
@RequiredArgsConstructor
public class BoardController {
    /** Logger */
    private final Logger logger = LoggerFactory.getLogger(BoardController.class.getName());

    /** 게시물 관련 처리 Service Layer 연결 */
    private final BoardService boardService;

    /**
     * 게시물 목록 조회 기능
     * @return 게시물 전체 목록
     */
    @GetMapping("")
    @Operation(summary = "게시물 목록 조회")
    public List<BoardDto> getViewBoardMain() {
        List<BoardDto> boardDtoList= boardService.getBoardList();
        return boardDtoList;
    }

    /**
     * 게시물 상세 조회 화면 이동
     * @param id 게시물 ID
     * @return 게시물 상세 조회 정보
     */
    @GetMapping("{id}")
    @Operation(summary = "게시물 상세 조회")
    public BoardDto getViewBoard(@PathVariable("id") int id) {
        BoardDto boardDetail = boardService.getBoard(id);
        return boardDetail;
    }

    /**
     * 게시물 등록 처리 기능
     * @param boardDto 게시물 등록 데이터
     */
    @PostMapping("")
    @Operation(summary = "게시물 등록")
    public void addBoard(@RequestBody BoardDto boardDto) {
        boardService.addBoard(boardDto);
    }

    /**
     * 게시물 수정 처리
     * @param id 수정 대상 게시물 ID
     * @param boardDto 게시물 수정 데이터
     */
    @PutMapping("{id}")
    @Operation(summary = "게시물 수정")
    public void modBoard(
            @PathVariable("id") int id,
            @RequestBody BoardDto boardDto) {
        boardDto.setId(id);
        boardService.modBoard(boardDto);
    }

    /**
     * 게시물 삭제 처리 기능
     * @param id 삭제 대상 게시물 ID
     */
    @DeleteMapping("{id}")
    @Operation(summary = "게시물 삭제")
    public void removeBoard(@PathVariable("id") int id) {
        boardService.removeBoard(id);
    }
}
