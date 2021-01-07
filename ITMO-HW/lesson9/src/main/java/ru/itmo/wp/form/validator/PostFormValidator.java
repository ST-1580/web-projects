package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.form.PostForm;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PostFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PostForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (!errors.hasErrors()) {
            PostForm enterForm = (PostForm) o;
            List<String> tags = Arrays.stream(enterForm.getTags().split("\\s"))
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            List<Tag> validatedTags = new ArrayList<>(List.of());
            for (String s : tags) {
                if (s.matches("[a-z]*")) {
                    if (!s.equals("")) {
                        Tag tag = new Tag();
                        tag.setName(s);
                        validatedTags.add(tag);
                    }
                } else {
                    errors.rejectValue("tags", "tags.wrong-tag", "Wrong tag: " + s);
                    return;
                }
            }
            enterForm.setValidatedTags(Set.copyOf(validatedTags));
        }
    }
}
