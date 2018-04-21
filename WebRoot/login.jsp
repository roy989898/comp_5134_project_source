<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
  <head>  
    <title>LOG IN</title>  
   
        <link rel="stylesheet" href="css/style.css">
        
  </head>  
  <body >  
  <div class="page-container">
  		<div style="margin-top:5px; text-align: center;">
  			<p style="font-size: 28px; font-weight: bold;">Course Selecting System</p>
  			
         	<form action="Login"  method="post" style="font-size: 18px; margin-top: 50px;" >  
      			  &nbsp;ACCOUNT：<input type="text" name="name"value=""><br><br>  
    			 PASSWORD：<input type="password" name="pwd"value=""><br><br>  
             		  <input id="login" type="submit"value="login"name="login">&nbsp;&nbsp;&nbsp;&nbsp;<input id="reset" type="reset" value="reset"><br>  
       		</form>  
  		</div>
  </div>
  </body>
</html>