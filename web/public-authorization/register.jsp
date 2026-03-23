<%-- 
    Document   : register
    Created on : Mar 18, 2026, 6:38:35 AM
    Author     : raren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
    </head>
    
        
    <body>
        
        <h1 class="shiftRight250px">Registration</h1>
        
        <header class="shiftRight250px">
            <strong>Register For Bytespace</strong>
        </header>
        
        <div>
            <nav class="floatNavLeft"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></nav>
        </div>
        
        <main class="clearFloat">
            <form class="member-register-form" action="${pageContext.request.contextPath}/Public" method="post">
               
                <input type="hidden" name="action" value="register"/>
                <input type="hidden" name="role" value="MEMBER"/>
                
                <div>
                    <label>Username</label>
                    <input type="text" name="username" id="username"/>
                </div>
                
                <div>
                    <label>Firstname</label>
                    <input type="text" name="firstname" id="firstname"/>
                </div>
                
                <div>
                    <label>middlename</label>
                    <input type="text" name="middlename" id="middlename"/>
                </div>
                
                <div>
                    <label>Lastname</label>
                    <input type="text" name="lastname" id="lastname"/>
                </div>
                
                 <div>
                    <label>Password</label>
                    <input type="text" name="password" id="password"/>
                 </div>
                
                <div>
                    <label>Confirm Password</label>
                    <input type="text" name="confPassword" id="confPassword"/>
                </div>
                <div>
                    <input type="submit" value="Register"/>
                </div>
                
            </form>
        </main>
            
        <span>${errors}</span>
        <span>${messages}</span>

       <footer class="shiftRight250px">
             <small><i>Copyright &copy; 2026 bytepace</i></small>
        </footer>
    </body>
</html>
