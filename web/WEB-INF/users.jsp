<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </head>
    <body>
        <br>
        <h1 style='text-align: center'>Alex's User Management System</h1>
    <div class="container">
        <div class="row">
            <div class="col">
                <form action="user" method="post">
                    <table class="table">
                    <thead>
                        <tr>
                            <th>E-mail</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Active</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.email}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.active ? "Y" : "N"}</td>
                                <td>
                                    <button>Edit</button>
                                    <button type="submit" name="action" value="delete?${user.email}">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                </form>
                
            </div>
        </div>
    </div>
    <div style="padding-left: 100px; align-content: center" >
        <h1>Add user</h1>
        <form action="user" method="post">
            <input type="hidden" name="action" value="add">
            <div class="m-1">
                <input type="email" name="email" id="email" placeholder="Email">
            </div>
            <div>
                <input type="text" name="firstName" id="firstName" placeholder="First name">
            </div>
            <div>
                <input type="text" name="lastName" id="lastName" placeholder="Last name">
            </div>
            <div>
                <input type="password" name="password" id="password" placeholder="Password">
            </div>
            <div >
                <select name="role" id="role">
                    <option>Choose your role</option>
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.name}">${role.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div text-align:center>
                <button type="submit">Add User</button>
            </div>
        </form>
        <p>${message}</p>
    </div>

</body>
</html>
