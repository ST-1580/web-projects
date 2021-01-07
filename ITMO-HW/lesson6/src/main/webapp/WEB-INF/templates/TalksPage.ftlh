<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.domain.Talk[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->

<#import "commons.ftlh" as c>

<@c.page>

    <form method="post" class="talker" action="">
        <input type="hidden" name="action" value="sendMessage"/>
        <div class="select">
            Message to
            <select name="targetUserLogin">
                <#list users as u>
                    <#if u.id != user.id>
                        <option>${u.login}</option>
                    </#if>
                </#list>
            </select>
        </div>
        <div class="text">
            <textarea maxlength="255" name="text"></textarea>
        </div>
        <#if error??>
            <div class="error">
                ${error!}
            </div>
        </#if>
        <input class="sendIt" type="submit" value="Send it">
    </form>

    <div class="datatable">
        <div class="caption">Talks</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>SourceId</th>
                <th>TargetId</th>
                <th>Text</th>
                <th>CreationTime</th>
            </tr>
            </thead>
            <#if talks?has_content>
                <tbody>
                <#list talks as message>
                    <#if user.id == message.sourceUserId || user.id == message.targetUserId>
                        <tr>
                            <td>${message.id}</td>
                            <td>${message.sourceUserId}</td>
                            <td>${message.targetUserId}</td>
                            <td>${message.text}</td>
                            <td>${message.creationTime}</td>
                        </tr>
                    </#if>
                </#list>
                </tbody>
            </#if>
        </table>
    </div>
</@c.page>