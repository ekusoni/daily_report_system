<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />


<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commApp" value="${ForwardConst.CMD_APPROVAL.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>日報　詳細ページ</h2>


        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${report.employee.name}" /></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="reportDay" type="date" />
                    <td><fmt:formatDate value="${reportDay}" pattern='yyyy-MM-dd' /></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${report.content}" /></pre></td>
                </tr>
                <tr>
                    <th>出勤時刻</th>
                    <td><c:out value="${report.attendAt}" /></td>
                </tr>
                <tr>
                    <th>退勤時刻</th>
                    <td><c:out value="${report.leavingAt}" /></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${report.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${report.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>
        <br /><br />

        <c:choose>
            <c:when test="${report.deleteFlag ==2  and (sessionScope.login_employee.id==report.employee.id or sessionScope.login_employee.adminFlag==3 or sessionScope.login_employee.adminFlag==2)}">
                <table>
                    <tbody>
                        <tr>
                            <th>修正点</th>
                            <td>
                                <c:choose>
                                    <c:when test="${!empty report.correctionPoint}"><c:out value="${report.correctionPoint}" /></c:when>
                                    <c:otherwise>修正点の記述は無しです</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </c:when>
            <c:when test="${ report.deleteFlag ==2 and (sessionScope.login_employee.id==report.employee.id or sessionScope.login_employee.adminFlag==3)}">
                <table>
                    <tbody>
                        <tr>
                            <th>修正点</th>
                            <td>
                                <c:choose>
                                   <c:when test="${!empty report.correctionPoint}"><c:out value="${report.correctionPoint}" /></c:when>
                                   <c:otherwise>修正点の記述は無しです</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${sessionScope.login_employee.adminFlag== 2 and sessionScope.login_employee.organizationId==report.employee.organizationId and report.employee.adminFlag==0 and report.deleteFlag==0}">
                <form method="POST" action="<c:url value='/?action=${actRep}&command=${commApp}&id=${report.id}' />">
                    <button type="submit">承認</button>
                </form>
                <br /><br />
                <form method="POST" action="<c:url value='/?action=${actRep}&command=${commDel}&id=${report.id}' />">
                    <label for="${AttributeConst.REP_CORRECTION_POINT.getValue()}">修正点</label><br />
                    <textarea name="${AttributeConst.REP_CORRECTION_POINT.getValue()}"rows="10" cols="30"></textarea><br />
                    <button type="submit">非承認</button>
                </form>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${sessionScope.login_employee.adminFlag== 3 and sessionScope.login_employee.organizationId==report.employee.organizationId and report.employee.adminFlag==2 and report.deleteFlag== 0}">
                <form method="POST" action="<c:url value='/?action=${actRep}&command=${commApp}&id=${report.id}' />">
                    <button type="submit">承認</button>
                </form>
                <br /><br />
                <form method="POST" action="<c:url value='/?action=${actRep}&command=${commDel}&id=${report.id}' />">
                    <label for="${AttributeConst.REP_CORRECTION_POINT.getValue()}">修正点</label><br />
                    <textarea name="${AttributeConst.REP_CORRECTION_POINT.getValue()}"rows="10" cols="30"></textarea><br />
                    <button type="submit">非承認</button>
                </form>
            </c:when>
        </c:choose>


        <c:if test="${sessionScope.login_employee.id==report.employee.id}">
            <p>
                <a href="<c:url value='?action=${actRep}&command=${commEdit}&id=${report.id}' />">この日報を編集する</a>
            </p>
        </c:if>


        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>



    </c:param>

</c:import>