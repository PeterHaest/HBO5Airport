<%-- 
    Document   : TableTemplate
    Created on : 10-mei-2017, 10:34:27
    Author     : Peter
--%>

<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="tableHeader" fragment="true"%>
<%@attribute name="tableBody" fragment="true"%>

<%-- any content can be specified here e.g.: --%>
    <table cellpadding="0" cellspacing="0" border="0" id="myTable" class="tablecontainer tablesorter">
        <thead>  
            <jsp:invoke fragment="tableHeader"></jsp:invoke>
        </thead>
        <tbody>
            <jsp:invoke fragment="tableBody"></jsp:invoke>
        </tbody>
    </table>