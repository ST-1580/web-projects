<#import "commons.ftlh" as c>

<@c.page>

    <form class="makeArticle">
        <div class="title">
            Title: <input name="title">
        </div>
        <div class="text">
            <textarea name="text" maxlength="1023"></textarea>
        </div>
        <div class="hidden">
            <input id="checker" type="checkbox" name="hidden">
            <label for="checker">I want to hide my article</label>
        </div>
        <div class="error"></div>
        <input type="submit" value="Create">
    </form>
    <script>
        $(function () {
            $(".makeArticle").submit(function () {
                const title = $(this).find("input[name='title']").val();
                const text = $(this).find("textarea[name='text']").val();
                const isHide = $(this).find("input[name='hidden']").prop("checked");
                let $error = $(this).find(".error");

                myAjax("create", {title: title, text: text, isHide: isHide}, $error);

                return false;
            })
        })
    </script>
</@c.page>