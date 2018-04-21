<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib  uri="/WEB-INF/c.tld" prefix="c" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
    <title>select courses</title> 
    <script type="text/javascript" src="jquery-3.3.1.min.js"></script> 
    <style>
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {background-color: #f2f2f2;}
</style>
  </head>  
<a style="float:right; margin-right:30px" href="/select_course/login.jsp">Log out</a>
    
  <body style="text-align:center; margin-left:auto;margin-right:auto;">  


  <div style="margin-top: 50px; ">
  	<h1>${message}</h1> </div>
  	<div style="margin-top: 100px;"> 
  <table class="table">
  <tr>
    <th>Name</th>
    <th>Choose times</th>
    <th>days</th>
    <th>you have choosed</th>
  </tr>
  <tr>
    <td>${name}</td>
    <td>${chance}</td>
    <td>${days}</td>
     <td>${courseStr}</td>
  </tr>
  </table>

                   <h2>now you can choose:</h2>
	       <%-- <c:forEach var="course" items="${courseList}"  >   
	      <form action="UpdateServlet" method="post">   
	           <input type="text" value="${course}" name="course" >
	           <a href="DeleteServlet?id=">Delete</a>  <input type="submit" value="update"/>  
	    	</form>   
	    	</c:forEach>  --%>
	    	 
	    	
       		 	
  	<div class="row margin-top-20">
        <table class="table">
            <thead>
            <tr>
            <th>Selected</th>
                <th class="seq">Lesson</th>
              
            </tr>
            </thead>
            <tbody>
            </tbody>
            <c:forEach var="course" items="${ableList}" >
                <tr>
                    <td><input type="checkbox" name="mm" value=${course} style="margin-auto"/> </td>
                   <td>${course}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
           <br/>
		     <input id="confirm" type="button" value="confirm" name="btn"  style="margin-top: 20px;"/> 
    </div>
  	<hr style="margin-top:50px;">
  	<div style="margin-top:50px;">
  	
  	
 <h2>please charge:</h2>
  	
  	<table>
  <tr>
    <th>VALUE</th>
    <th>Courses</th>
  </tr>
   <tr>
    <td><input type="radio" name="gender" value="10" style="margin-top: 20px;">1000yuan</td>
    <td>10 courses</td>
   
  </tr>
    <tr>
    <td><input type="radio" name="gender" value="20" style="margin-top: 20px;">1500yuan</td>
    <td>20 courses</td>
   
  </tr>  
  <tr>
    <td><input type="radio" name="gender" value="30" style="margin-top: 20px;">2000yuan</td>
    <td>30 courses</td>
   
  </tr>
  </table>
  <input id="charge" type="button" value="charge" name="btn"  style="margin-top: 20px;"/>
 
  		
  
  </div>
  </body>  
  <script>
  $("#confirm").click(function() {
      courses = $("input:checkbox[name='mm']:checked").map(function(index,elem) {
          return $(elem).val();
      }).get().join(',');
      
      $.ajax({      
    	  type : "POST",      
    	  url : "${pageContext.request.contextPath}/ajaxSubmitCourse",   
    	  dataType : "text", 
    	  data : {"courses" : courses,
    		   	  "studentId": '${studentId}',
    		   	  "studentName": '${name}'
    	  		},     
    	  	success : function(result) {      
    		     alert(result);
    	  	}, 
	  		error : function(xhr, status, errMsg)
	        {
	             alert("failed!");
	        }
    	  });  
      
  });
  
  $("#charge").click(function() {
	  var chance = $('input:radio:checked').val();
	  $.ajax({      
    	  type : "POST",     
    	  url : "${pageContext.request.contextPath}/ajaxSubmitCourse",  
    	  dataType : "text", 
    	  data : {"chance" : chance,
    		   	  "studentId": '${studentId}',
    		   	  "studentName": '${name}'
    	  		},     
    	  	success : function(result) {      
    		     alert(result);
    	  	}, 
	  		error : function(xhr, status, errMsg)
	        {
	             alert("failed!");
	        }
   	  });  
  });
  
  
  </script>
</html>  