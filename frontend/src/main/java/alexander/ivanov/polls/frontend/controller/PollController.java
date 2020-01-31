package alexander.ivanov.polls.frontend.controller;

import alexander.ivanov.polls.frontend.service.PollService;
import alexander.ivanov.polls.frontend.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polls")
public class PollController {
    private static final Logger logger = LoggerFactory.getLogger(PollController.class);
    private PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping
    public ResponseEntity<?> getPolls() {
        /*List<Poll> polls = pollService.getPolls();
        *//*polls.forEach(poll -> {
            logger.info("poll = {}", poll);
        });*//*
        return ResponseEntity.ok(polls);*/
        return ControllerUtils.getResponseEntity(() -> pollService.getPolls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPollById(@PathVariable String id) {
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
}
