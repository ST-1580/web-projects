<#import "commons.ftlh" as c>

<@c.page>
    <form method="post">
        <label>
            <textarea class="text" name="text"></textarea>
        </label>
        <@c.error "noticeForm.text"/>
        <input type="submit" value="Add">
    </form>
</@c.page>