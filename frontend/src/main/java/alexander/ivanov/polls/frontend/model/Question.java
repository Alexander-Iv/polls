package alexander.ivanov.polls.frontend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(description = "All details about the Question.")
@Entity(name = "question")
@Table(name = "question")
public class Question {
    @ApiModelProperty(notes = "The database generated question ID")
    @Id
    @Column(name = "qstn_id")
    private Long id;

    @ApiModelProperty(notes = "The question")
    @Column(name = "content")
    private String content;

    @ApiModelProperty(notes = "The order witch questions are sorted")
    @Column(name = "order_num")
    private Integer orderNum;

    @ManyToOne @JoinColumn(name = "poll_poll_id") @JsonIgnore
    private Poll poll;

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", orderNum=" + orderNum +
//                ", poll=" + poll +
                '}';
    }
}
