package alexander.ivanov.polls.frontend.controller;

import alexander.ivanov.polls.frontend.model.dto.PollDto;
import alexander.ivanov.polls.frontend.service.PollService;
import alexander.ivanov.polls.frontend.util.ControllerUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "name.like",
                    value = "Filter by name with like operation",
                    dataTypeClass = Map.class,
                    example = "name.like=Опрос%"),
            @ApiImplicitParam(name = "startDate.[OPERATION]", value = "Filter by start date where [OPERATION] may be one of eq, gt, gte, lt, lte", example = "startDate.eq=2020-01-01T00:00:00"),
            @ApiImplicitParam(name = "endDate.[OPERATION]", value = "Filter by end date where [OPERATION] may be one of eq, gt, gte, lt, lte", example = "startDate.eq=2020-12-31T23:59:59"),
            @ApiImplicitParam(name = "activity.eq", value = "Filter by activity with eq operation", example = "activity.eq=true"),
            @ApiImplicitParam(name = "page", value = "Number of page", example = "page=0"),
            @ApiImplicitParam(name = "size", value = "Number of polls on page", example = "size=0"),
            @ApiImplicitParam(name = "sortBy[.OPERATION]", value = "Sort by poll field where [.OPERATION] is optional type sort asc or desc", example = "sortBy=name"),
    })
    @GetMapping
    public ResponseEntity<?> getPolls(@RequestParam Map<String, String> params) {
        return ControllerUtils.getResponseEntity(() -> pollService.getPolls(params));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved poll"),
            @ApiResponse(code = 400, message = "Some error occur")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getPollById(
            @ApiParam(value = "Poll id from which poll object will retrieve", required = true)
            @PathVariable String id) {
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
        return ControllerUtils.getResponseEntity(() -> pollService.createOrUpdatePoll(pollDto));
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
        return ControllerUtils.getResponseEntity(() -> pollService.createOrUpdatePoll(pollDto));
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
        return ControllerUtils.getResponseEntity(() -> pollService.deletePoll(id));
    }
}
