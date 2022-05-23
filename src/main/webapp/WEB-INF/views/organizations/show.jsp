<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <c:forEach var="organization" items="${organizations}">
            <c:if test="${sessionScope.login_employee.organizationId==organization.id}">
                <h2><c:out value="${organization.departmentName}"></c:out>部関係者一覧</h2>
            </c:if>
        </c:forEach>


            <h3>部長</h3>
            <div class="A">
                <c:forEach var="employee" items="${employees}">
                    <c:if test="${sessionScope.login_employee.organizationId==employee.organizationId and employee.adminFlag==3}">
                        <c:choose>
                            <c:when test="${sessionScope.login_employee.id==employee.id }"><div class="B"><span class="login_employee"><c:out value="${employee.name}"></c:out></span></div>&nbsp;&nbsp;&nbsp;&nbsp;</c:when>
                            <c:otherwise><div class="B"><c:out value="${employee.name}"></c:out></div>&nbsp;&nbsp;&nbsp;&nbsp;</c:otherwise>
                        </c:choose>
                    </c:if>
                </c:forEach>
            </div>
            <br /><br />

            <h3>課長</h3>
            <div class="A">
                <c:forEach var="employee" items="${employees}">
                    <c:if test="${sessionScope.login_employee.organizationId==employee.organizationId and employee.adminFlag==2}">
                        <c:choose>
                            <c:when test="${sessionScope.login_employee.id==employee.id }"><div class="B"><span class="login_employee"><c:out value="${employee.name}"></c:out></span>&nbsp;&nbsp;&nbsp;&nbsp;</div></c:when>
                            <c:otherwise><div class="B"><c:out value="${employee.name}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</div></c:otherwise>
                        </c:choose>
                     </c:if>
                </c:forEach>
            </div>
            <br /><br />

            <h3>一般</h3>
            <div class="A">
                <c:forEach var="employee" items="${employees}">
                    <c:if test="${sessionScope.login_employee.organizationId==employee.organizationId and employee.adminFlag==0}">
                        <c:choose>
                            <c:when test="${sessionScope.login_employee.id==employee.id }"><div class="B"><span class="login_employee"><c:out value="${employee.name}"></c:out></span>&nbsp;&nbsp;&nbsp;&nbsp;</div></c:when>
                            <c:otherwise><div class="B"><c:out value="${employee.name}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</div></c:otherwise>
                        </c:choose>
                    </c:if>
                </c:forEach>
            </div>
            <br /><br />



            <h3>管理者</h3>
            <div class="A">
                <c:forEach var="employee" items="${employees}">
                    <c:if test="${sessionScope.login_employee.organizationId==employee.organizationId and employee.adminFlag==1}">
                        <c:choose>
                            <c:when test="${sessionScope.login_employee.id==employee.id }"><div class="B"><span class="login_employee"><c:out value="${employee.name}"></c:out></span>&nbsp;&nbsp;&nbsp;&nbsp;</div></c:when>
                            <c:otherwise><div class="B"><c:out value="${employee.name}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</div></c:otherwise>
                        </c:choose>
                    </c:if>
                </c:forEach>
            </div>
            <br /><br />
    </c:param>
</c:import>
