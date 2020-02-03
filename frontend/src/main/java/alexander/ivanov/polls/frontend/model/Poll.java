package alexander.ivanov.polls.frontend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(description = "All details about the Poll.")
@Entity(name = "poll")
@Table(name = "poll")
public class Poll {
    @ApiModelProperty(notes = "The database generated poll ID")
    @Id
    @Column(name = "poll_id")
    private Long id;

    @ApiModelProperty(notes = "The poll name")
    @Column(name = "poll_name")
    private String name;

    @ApiModelProperty(notes = "The poll start date")
    @Column(name = "start_date")
    private Date startDate;

    @ApiModelProperty(notes = "The poll end date")
    @Column(name = "end_date")
    private Date endDate;

    @ApiModelProperty(notes = "The poll activity")
    @Column(name = "activity")
    private Boolean activity;

    @ApiModelProperty(notes = "The questions linked with poll")
    @OneToMany(mappedBy = "poll", fetch = FetchType.EAGER, targetEntity = Question.class)
    @OrderBy("orderNum")
    private List<Question> questions;

    public Poll() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getActivity() {
        return activity;
    }

    public void setActivity(Boolean activity) {
        this.activity = activity;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        questions.forEach(question -> question.setPoll(this));
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", activity=" + activity +
                '}';
    }
}
