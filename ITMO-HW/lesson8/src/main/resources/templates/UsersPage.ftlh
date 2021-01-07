<#-- @ftlvariable name="users" type="ru.itmo.wp.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <#if user??>
                    <th>Disable\Enable</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as currUser>
                    <form method="post">
                        <tr>
                            <input type="hidden" name="id" value="${currUser.id}">
                            <td class="id">${currUser.id}</td>
                            <td class="login">${currUser.login}</td>
                            <td class="creationTime">${currUser.creationTime}</td>
                            <#if user??>
                                <#if user != currUser>
                                    <#if currUser.disabled>
                                        <td class="disabled"><input type="submit" name="action" value="Enable"></td>
                                    <#else>
                                        <td class="disabled"><input type="submit" name="action" value="Disable"></td>
                                    </#if>
                                <#else>
                                    <td class="disabled">You can't disable yourself</td>
                                </#if>
                            </#if>
                        </tr>
                    </form>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
