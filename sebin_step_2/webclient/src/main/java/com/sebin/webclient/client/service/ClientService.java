package com.sebin.webclient.client.service;

import com.sebin.webclient.client.controller.ClientController;
import com.sebin.webclient.client.dto.BoardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class.getName());

    WebClient webclient = WebClient.create("http://localhost:8081/board");

    public BoardDto addBoardNew(BoardDto boardDto) {
        logger.info("등록 client");
        logger.info("등록 dto" + boardDto.getContents());

        return webclient.post()
                .uri("")
                .bodyValue(boardDto)
                .retrieve()
                .bodyToMono(BoardDto.class)
                .block();
    }

    public BoardDto removeBoard(Integer id){
        logger.info("삭제 client");
        logger.info("삭제 client" + id);
        return webclient.delete()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(BoardDto.class)
                .block();
    }

    public BoardDto modBoard(Integer id, BoardDto boardDto){
        logger.info("수정 client");
        boardDto.setId(id);
        return webclient.put()
                .uri("/" + id)
                .bodyValue(boardDto)
                .retrieve()
                .bodyToMono(BoardDto.class)
                .block();
    }
}
