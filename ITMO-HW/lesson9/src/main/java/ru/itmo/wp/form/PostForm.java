package ru.itmo.wp.form;

import org.springframework.core.metrics.StartupStep;
import ru.itmo.wp.domain.Tag;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class PostForm {
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 60)
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    @Lob
    private String text;

    private String tags;

    private Set<Tag> validatedTags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Set<Tag> getValidatedTags() {
        return validatedTags;
    }

    public void setValidatedTags(Set<Tag> validatedTags) {
        this.validatedTags = validatedTags;
    }
}
