package com.potatoes.bloodrecovery.domain.model.valueobjects;

import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

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
    @CreatedDate
    private LocalDateTime regDate;

    public Post(RegisterBloodRequestCommand registerBloodRequestCommand) {
        this.title = registerBloodRequestCommand.getTitle();
        this.contents = registerBloodRequestCommand.getContents();
    }

    public Post(ModifyBloodRequestCommand modifyBloodRequestCommand) {
        this.title = modifyBloodRequestCommand.getTitle();
        this.contents = modifyBloodRequestCommand.getContents();
    }
}
