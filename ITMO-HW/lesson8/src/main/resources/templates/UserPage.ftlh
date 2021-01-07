<#-- @ftlvariable name="currUser" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if currUser??>
        <div class="userInfo">
            <div>Id: ${currUser.id}</div>
            <div>Login: ${currUser.login}</div>
            <div>Creation time: ${currUser.creationTime}</div>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>
