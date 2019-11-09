<%-- 
    Document   : user-form-html
    Created on : May 6, 2019, 6:34:27 PM
    Author     : SaT_LoP
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/webjars/bootstrap/3.4.1/css/bootstrap.min.css"/>" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12" style="text-align: center">
                    <h2>Book Form</h2>
                </div>
            </div>
            <c:if test="${message} !=null && message !=''">
                <div class="alert alert-danger">
                    ${message}
                </div>
            </c:if>
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <mvc:form action="${pageContext.request.contextPath}/${action}" method="post" modelAttribute="book" class="form-horizontal">
                        <c:if test="${action == 'update-book'}">
                            <input name="id" value="${book.id}" hidden/>
                            <input name="bookDetail.id" value="${book.bookDetail.id}" hidden/>
                        </c:if>
                        <div class="form-group">
                            <label class="control-lable col-sm-2">
                                Name
                            </label>
                            <div class="col-sm-8">
                                <input name="name" type="text" class="form-control required" value="${book.name}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-lable col-sm-2">
                                Author
                            </label>
                            <div class="col-sm-8">
                                <input name="author" type="text" class="form-control required" value="${book.author}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-lable col-sm-2">
                                ISBN
                            </label>
                            <div class="col-sm-8">
                                <input name="bookDetail.isbn" type="text" class="form-control required" value="${book.bookDetail.isbn}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-lable col-sm-2">
                                Number of Page
                            </label>
                            <div class="col-sm-8">
                                <input name="bookDetail.numberOfPage" type="text" class="form-control required" value="${book.bookDetail.numberOfPage}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-lable col-sm-2">
                                Description
                            </label>
                            <div class="col-sm-8">
                                <textarea name="bookDetail.description" type="text" class="form-control required" >
                                ${book.bookDetail.description}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-lable col-sm-2">
                                Price
                            </label>
                            <div class="col-sm-8">
                                <input name="bookDetail.price" type="text" class="form-control required" value="${book.bookDetail.price}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-lable col-sm-2">
                                Publish Date
                            </label>
                            <div class="col-sm-8">
                                <input name="bookDetail.publishDate" type="date" class="form-control required" value="${book.bookDetail.publishDate}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-lable col-sm-2">
                                Category
                            </label>
                            <div class="col-sm-8">
                                <select name="category.id" class="form-control">
                                    <c:forEach items="${categories}" var="c">
                                        <c:if test="${c.id == book.category.id}">
                                            <option value="${c.id}" selected>${c.name}</option>
                                        </c:if>
                                        <c:if test="${c.id != book.category.id}">
                                            <option value="${c.id}">${c.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <c:if test="${action == 'add-book'}">
                                <button class="btn btn-primary" type="submit">
                                    Add
                                </button>
                            </c:if>
                            <c:if test="${action == 'update-book'}">
                                <button class="btn btn-primary" type="submit">
                                    Update
                                </button>
                            </c:if>
                        </div>
                    </mvc:form>
                </div>
            </div>
        </div>
    </body>
</html>
