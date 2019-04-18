<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath().toString();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Spring MVC 4 + Ajax Hello World</title>

<link href="<%=contextPath%>/assets/css/bootstrap.min.css" rel="stylesheet" />
<script src="<%=contextPath%>/assets/js/jquery-1.11.3.js"></script>
<script src="<%=contextPath%>/assets/js/bootstrap.min.js"></script>
</head>

<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Spring 4 MVC Ajax Hello World</a>
		</div>
	</div>
</nav>

<div class="container" style="min-height: 500px">

	<div class="starter-template">
		<h1>Search Form</h1>
		<br>

		<div id="feedback"></div>

		<form class="form-horizontal" id="search-form">
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">Username</label>
				<div class="col-sm-10">
					<input type=text class="form-control" id="username">
				</div>
			</div>
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">Address</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="address">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="bth-search" class="btn btn-primary btn-lg">Search</button>
					<button type="submit" id="bth-search-private" class="btn btn-primary btn-lg">Search Private</button>
				</div>
			</div>
		</form>

	</div>

</div>

<div class="container">
	<footer>
		<p>
			&copy; <a href="http://www.jaesonchen.com">jaesonchen.com</a> 2015
		</p>
	</footer>
</div>

<script>
	jQuery(document).ready(function($) {
		$("#bth-search").click(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			searchUser();
		});
		
		$("#bth-search-private").click(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            searchUserPrivate();
        });
	});
	
	function searchUser() {
		var searchText = {};
		searchText["username"] = $("#username").val();
		searchText["address"] = $("#address").val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "<%=contextPath%>/api/search",
			data : JSON.stringify(searchText),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			}
		});
	}

	function searchUserPrivate() {
        var searchText = {};
        searchText["username"] = $("#username").val();
        searchText["address"] = $("#address").val();
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "<%=contextPath%>/api/search/private",
            data : JSON.stringify(searchText),
            dataType : 'json',
            timeout : 100000,
            success : function(data) {
                console.log("SUCCESS: ", data);
                display(data);
            },
            error : function(e) {
                console.log("ERROR: ", e);
                display(e);
            }
        });
    }
	
	function display(data) {
		var json = "<h4>Ajax Response</h4><pre>"
				+ JSON.stringify(data, null, 4) + "</pre>";
		$('#feedback').html(json);
	}
</script>

</body>
</html>