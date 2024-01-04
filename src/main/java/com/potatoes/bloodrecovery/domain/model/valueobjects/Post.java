package com.potatoes.bloodrecovery.domain.model.valueobjects;

import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

import static com.potatoes.constants.StaticValues.REQUEST;

@Slf4j
@Getter
@Builder
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private String title;
    private String contents;
    private String postStatus; //todo enum으로 따로 만들자
    @CreatedDate
    private LocalDateTime regDate;

    public Post(RegisterBloodRequestCommand registerBloodRequestCommand) {
        this.title = registerBloodRequestCommand.getTitle();
        this.contents = registerBloodRequestCommand.getContents();
        this.postStatus = REQUEST;
    }
}
