package alexander.ivanov.polls.frontend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "question")
@Table(name = "question")
public class Question {
    @Id
    @Column(name = "qstn_id")
    private Long id;
    @Column(name = "content")
    private String content;
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
                ", poll=" + poll +
                '}';
    }
}
