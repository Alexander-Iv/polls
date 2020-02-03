package alexander.ivanov.polls.frontend.controller;

import alexander.ivanov.polls.frontend.model.dto.PollDto;
import alexander.ivanov.polls.frontend.service.PollService;
import alexander.ivanov.polls.frontend.util.ControllerUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("List of polls")
@RestController
@RequestMapping("/api/polls")
public class PollController {
    private static final Logger logger = LoggerFactory.getLogger(PollController.class);
    private PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @ApiOperation(value = "View a list of available polls", response = ResponseEntity.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "Some error occur")
    })
    @GetMapping
    public ResponseEntity<?> getPolls(@RequestParam Map<String, String> params) {
        /*List<Poll> polls = pollService.getPolls();
        *//*polls.forEach(poll -> {
            logger.info("poll = {}", poll);
        });*//*
        return ResponseEntity.ok(polls);*/
        return ControllerUtils.getResponseEntity(() -> pollService.getPolls(params));
    }

    @ApiOperation(value = "Get an available poll by Id", response = ResponseEntity.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved poll"),
            @ApiResponse(code = 400, message = "Some error occur")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getPollById(
            @ApiParam(value = "Poll id from which poll object will retrieve", required = true)
            @PathVariable String id) {
        /*Poll poll;
        try {
            poll = pollService.getPollById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
            //return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
        return ResponseEntity.ok(poll);*/
        return ControllerUtils.getResponseEntity(() -> pollService.getPollById(id));
    }

    @ApiOperation(value = "Add a poll")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully add poll"),
            @ApiResponse(code = 400, message = "Some error occur")
    })
    @PostMapping
    public ResponseEntity<?> createPoll(
            @ApiParam(value = "Poll object which will created")
            @RequestBody PollDto pollDto) {
        return ControllerUtils.getResponseEntity(() -> pollService.createPoll(pollDto));
    }

    @ApiOperation(value = "Update a poll")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully update poll"),
            @ApiResponse(code = 400, message = "Some error occur")
    })
    @PutMapping
    public ResponseEntity<?> updatePoll(
            @ApiParam(value = "Poll object which will updated")
            @RequestBody PollDto pollDto) {
        return ResponseEntity.ok("");
    }

    @ApiOperation(value = "Delete a poll")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully delete poll"),
            @ApiResponse(code = 400, message = "Some error occur")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePoll(
            @ApiParam(value = "Poll object which will deleted", required = true)
            @PathVariable String id) {
        return ResponseEntity.ok("");
    }
}
