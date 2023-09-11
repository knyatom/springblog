package com.example.blogproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data //Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
public class Reply {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 시퀀스 , auto_increment

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

   // @OneToMany(mappedBy="board", fetch=FetchType.EAGER)
   // private List<Reply> replys;
 // board는 reply에 있는 board를 적어준다.  기본값은 LAZY이다.

    @CreationTimestamp
    private Timestamp createDate;

    public void update(User user, Board board, String content){
        setUser(user);
        setBoard(board);
        setContent(content);
    }

}
