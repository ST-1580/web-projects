<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->

<#import "commons.ftlh" as c>

<@c.page>
    <div class="datatable">
        <div class="caption">My articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hide/Show</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as article>
                <tr>
                    <td>${article.id}</td>
                    <td>${article.title}</td>
                    <#if article.hide>
                        <td><input class="HideOrShow" id="${article.id}" type="button" value="Show">
                    <#else>
                        <td><input class="HideOrShow" id="${article.id}" type="button" value="Hide">
                    </#if>

                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <script>
        $(function () {
            $(".HideOrShow").click(function () {
                let id = this.id;
                let $place = $(this);

                $.ajax({
                    type: "POST",
                    url: "",
                    dataType: "json",
                    data: {
                        action: "reload",
                        id: id
                    },
                    success: function (response) {
                        $place.val(response["value"]);
                    }
                });

                return false;
            })
        })
    </script>
</@c.page>