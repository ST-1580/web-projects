<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <#if user??>
            <div class="caption" id="${user.id}">User</div>
        <#else>
            <div class="caption">User</div>
        </#if>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if user?? && user.admin>
                    <th>Admin parameters</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user_id"></td>
                    <td class="user_login"></td>
                    <#if user?? && user.admin>
                        <td class="user_admin">
                            <div class="adminChecker">
                                <label for="smth"></label>
                                <input class="changeAdmin" id="smth" type="button" value="">
                            </div>
                        </td>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <#if user?? && user.admin>
                <td colspan="3">
                    <#else>
                <td colspan="2">
                    </#if>
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            const $noData = $(".users .noData");
            $noData.find("td").text("Loading...");
            let currUserId = $(".users .caption").attr("id");

            $.ajax({
                dataType: "json",
                type: "POST",
                data: {
                    action: "findAll"
                },
                success: function (response) {
                    response["users"].forEach(function (user) {
                        $noData.hide();
                        const $tr = $($(".users template").prop("content")).clone();
                        $tr.find(".user_id").text(user["id"]);
                        $tr.find(".user_login").text(user["login"]);
                        let text;
                        let buttonField;
                        if (user["admin"]) {
                            text = "admin";
                            buttonField = "disable"
                        } else {
                            text = "not admin";
                            buttonField = "enable"
                        }
                        if (user["id"] != currUserId) {
                            $tr.find(".user_admin label").text(text);
                            $tr.find(".user_admin input").val(buttonField);
                        } else {
                            $tr.find(".user_admin label").text("You can't disable yourself");
                            $tr.find(".user_admin input").hide();
                        }
                        $tr.find(".user_admin input").attr("data-userId", user["id"]);
                        $(".users tbody").append($tr);
                    });

                    $(".users .adminChecker").click(function () {
                        let $input = $(this).find("input");
                        let $label = $(this).find("label");
                        let id = $input.attr("data-userid");
                        let value = $(this).find("input").val();

                        if (id != currUserId) {
                            $.ajax({
                                type: "POST",
                                url: "",
                                dataType: "json",
                                data: {
                                    action: "setAdmin",
                                    id: id,
                                    value: value
                                },
                                success: function (response) {
                                    if (response["admin"]) {
                                        $input.val("disable");
                                        $label.text("admin")
                                    } else {
                                        $input.val("enable");
                                        $label.text("not admin")
                                    }
                                }
                            })
                        }
                    });
                }
            });
        })
    </script>
</@c.page>
