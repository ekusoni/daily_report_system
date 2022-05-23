<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>


<c:set var="actOrg" value="${ForwardConst.ACT_ORG.getValue()}" />

<c:set var="commCre" value="${ForwardConst.CMD_CREATE.getValue()}" />

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <c:if test="${errors !=null}">
            <div id="flush_error">
                入力内容にエラーがあります<br />
                <c:forEach var="error" items="${errors}">
                    ・<c:out value="${error}" /><br />
                </c:forEach>
            </div>
        </c:if>


        <h2>部署　登録</h2>
        <form method="POST" action="<c:url value='/?action=${actOrg}&command=${commCre}'  />">
            <label for="${AttributeConst.ORG_DEP_NAME.getValue()}">部署</label><br />
            <input type="text" name="${AttributeConst.ORG_DEP_NAME.getValue()}" />部<br /><br />
            <button type="submit">登録</button>

        </form>










    </c:param>
</c:import>
