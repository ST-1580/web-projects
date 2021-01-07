<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#-- @ftlvariable name="comment-form" type="ru.itmo.wp.domain.Comment" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if post??>
        <@c.post post=post/>
        <#if user??>
            <form class="comment-form" method="post">
                <div class="pls">Write a comment</div>
                <div class="text"><label>
                        <textarea name="text"></textarea>
                    </label></div>
                <@c.error "commentForm.text"/>
                <input class="button" type="submit" name="addComment" value="Add">
            </form>
        </#if>
        <div class="comment-list">Comments:</div>
        <#list post.comments as comment>
            <div class="comment">
                <div class="info">By ${comment.user.login}</div>
                <div class="text">${comment.text}</div>
            </div>
        </#list>
    <#else>
        No such post
    </#if>

</@c.page>