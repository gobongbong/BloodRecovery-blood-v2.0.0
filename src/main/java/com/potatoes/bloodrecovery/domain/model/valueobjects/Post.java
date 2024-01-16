package com.potatoes.bloodrecovery.domain.model.valueobjects;

import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import com.potatoes.constants.PostStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

import static com.potatoes.constants.PostStatus.*;

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
    private PostStatus postStatus;
    @CreatedDate
    private LocalDateTime regDate;

    public Post(RegisterBloodRequestCommand registerBloodRequestCommand) {
        this.title = registerBloodRequestCommand.getTitle();
        this.contents = registerBloodRequestCommand.getContents();
        this.postStatus = REGISTER;
    }

    public Post(ModifyBloodRequestCommand modifyBloodRequestCommand) {
        this.title = modifyBloodRequestCommand.getTitle();
        this.contents = modifyBloodRequestCommand.getContents();
    }

    public boolean isModifiable(){
        return !this.postStatus.equals(DIRECTED_DONATION_ONGOING) && !this.postStatus.equals(ONGOING);
    }

    public void changeStatus(PostStatus postStatus){
        this.postStatus = postStatus;
    }
}
