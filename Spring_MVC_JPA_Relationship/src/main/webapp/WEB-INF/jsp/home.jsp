<%-- 
    Document   : home
    Created on : May 8, 2019, 7:41:11 PM
    Author     : SaT_LoP
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="<c:url value="/webjars/bootstrap/3.4.1/css/bootstrap.min.css"/>" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12" style="text-align: center">
                    <h2>List Book</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-6 col-sm-6" style="padding-bottom: 10px; text-align: left">
                    <button class="btn btn-primary" onclick="location.href = '<c:url value="/add"/>'">Add User</button>
                </div>
                <div class="col-xs-6 col-sm-6" style="padding-bottom: 10px; text-align: right">
                    <form action="${pageContext.request.contextPath}/search" method="post" class="form-inline">
                        <div class="form-group">
                            <input name="searchText" class="form-control" type="text"/>
                            <input type="submit" class="btn btn-info" value="search"/>
                        </div>
                    </form>
                </div>
            </div>
            <c:if test="${status == 'fail'}">
                <div class="alert alert-danger">${message}</div>
            </c:if>
            <c:if test="${status == 'ok'}">
                <div class="alert alert-success">${message}</div>
            </c:if>
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <tr>
                                <th>Book Name</th>
                                <th>Author</th>
                                <th>Category</th>
                                <th>ISBN</th>
                                <th>Description</th>
                                <th>Number of Page</th>
                                <th>Price</th>
                                <th>Publish Date</th>
                                <th>Action</th>
                            </tr>
                            <c:if test="${books != null && fn:length(books)>0}">
                                <c:forEach items="${books}" var="book">
                                    <tr>
                                        <td>${book.name}</td>
                                        <td>${book.author}</td>
                                        <td>${book.category.name}</td>
                                        <td>${book.bookDetail.isbn}</td>
                                        <td>${book.bookDetail.description}</td>
                                        <td>${book.bookDetail.numberOfPage}</td>
                                        <td><fmt:formatNumber pattern="###,###" value="${book.bookDetail.price}"/> VNĐ</td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${book.bookDetail.publishDate}"/></td>
                                        <td>
                                            <button class="btn btn-warning" onclick="location.href = '<c:url value="/update/${book.id}"/>'">Update</button>
                                            <button class="btn btn-danger" onclick="location.href = '<c:url value="/delete/${book.id}"/>'">Delete</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${books == null || fn:length(books)<=0}">
                                <tr>
                                    <td class="bg-warning" colspan="9" style="color: red; text-align: center">No record</td>
                                </tr>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
