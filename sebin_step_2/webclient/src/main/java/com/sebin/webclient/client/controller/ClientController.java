package com.sebin.webclient.client.controller;


import com.sebin.webclient.client.dto.BoardDto;
import com.sebin.webclient.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService ClientService;
    /** Logger */
    private final Logger logger = LoggerFactory.getLogger(ClientController.class.getName());

    /** WebClient */
    WebClient webclient = WebClient.create("http://localhost:8081/board");

    @GetMapping("")
    public String goBoardHome(){
        logger.info("목록화면");
        return "board/main";
    }

    @GetMapping("get/list")
    public Flux<BoardDto> getAll(){
        logger.info("목록조회 client");
        return webclient.get()
                .uri("")
                .retrieve()
                .bodyToFlux(BoardDto.class);
    }

    @GetMapping("{id}")
    public String goDetail(@PathVariable Integer id){
        logger.info("상세화면");
        return "board/info";
    }

    @GetMapping("{id}/get/detail")
    public Flux<BoardDto> getDetail(@PathVariable Integer id){
        logger.info("상세조회 client");
        return webclient.get()
                .uri("/" + id)
                .retrieve()
                .bodyToFlux(BoardDto.class);
    }

    @GetMapping("new")
    public String goBoardNew(){
        logger.info("등록화면");
        return "board/new";
    }

    @PostMapping("save/new")
    public String addBoardNew(BoardDto boardDto) {
        ClientService.addBoardNew(boardDto);
        return "redirect:/board";
    }

    @DeleteMapping("{id}/delete")
    public String removeBoard(@PathVariable Integer id){
        logger.info("삭제 controller" + id);
        ClientService.removeBoard(id);
        return "redirect:/board";

    }

    @GetMapping("{id}/edit")
    public String goBoardEdit(@PathVariable Integer id){
        logger.info("수정화면");
        return "board/edit";
    }

    @PutMapping("{id}/edit/info")
    public String modBoard(@PathVariable Integer id, BoardDto boardDto){
        logger.info("수정 controller");
        ClientService.modBoard(id, boardDto);
        return "redirect:/board";
    }
}
