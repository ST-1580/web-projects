<#import "commons.ftlh" as c>

<@c.page>
    <div class="dropArticles">
        <div class="root"></div>
        <template>
            <article>
                <div class="title"></div>
                <div class="text"></div>
            </article>
        </template>
    </div>
    <script>
        $(function () {
            let $place = $(".dropArticles");

            $.ajax({
                type: "POST",
                url: "",
                dataType: "json",
                data: {
                    action: "findAll"
                },
                success: function (response) {
                    response["articles"].forEach(function(article) {
                        let $tpl = $($place.find("template").prop("content")).clone();

                        $tpl.find(".title").text(article["title"]);
                        $tpl.find(".text").text(article["text"]);

                        $place.find(".root").append($tpl);
                    })
                }
            });

            return false;
        })
    </script>
</@c.page>