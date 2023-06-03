<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.sql.*"%>
<%@page import="com.notifications.domain.Notification"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon"
	href="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/BMW.svg/1200px-BMW.svg.png"
	type="image/x-icon">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notifications</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
<link href="styles/sharedStyles.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-1.12.3.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

<script>
	$(document).ready(function() {
		$('#myTable').dataTable();

	});
</script>
</head>
<body>
	<%
		HttpSession sessions = request.getSession();
	%>
	<form action="navigate.do">
		<input type="submit" name="action" value="HOME" align="right">

	</form>
	<c:if test="${requestScope.Err!=null}">
		<h3 style="text-align: center">
			<font color="black">${requestScope.Err}</font>
		</h3>
	</c:if>
	<h2>Order Results</h2>
	<div class="table-responsive">
		<table id="myTable" class="display table">
			<thead>
				<tr>
					<th>Order_Id</th>
					<th>Ts_modification(24h)</th>
					<th>Notification_Type</th>
					<th>Notif_Action</th>
					<th>Notif_item_number</th>
					<th>notif_docdate</th>
					<th>vehicle_id_7</th>
					<th>mf_order_no</th>
					<th>vehicle_state</th>
					<th>client</th>
					<th>product_type</th>
					<th>brand</th>
					<th>mf_model_code</th>
					<th>ws_model_code</th>
					<th>order_type</th>
					<th>ivs_order_status</th>
					<th>processing_type</th>
					<th>ws_business_type</th>
					<th>nv_mf_orderer_ca</th>
					<th>nv_mf_orderer_id</th>
					<th>orderer_domestic</th>
					<th>invoicing_dealer</th>
					<th>mod_function</th>
					<th>mod_system</th>
					<th>mod_user</th>
					<th>version</th>
					<th>partition_key</th>
					<th>dealer_class</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<Notification> notificationList = (List<Notification>) sessions.getAttribute("listAferTransaction");
					for (Notification cx : notificationList) {
				%>
				<tr>
					<td><%=cx.getNv_ws_order_id()%></td>
					<td><%=cx.getTs_modification()%></td>
					<td><%=cx.getNotification_type()%></td>
					<td><%=cx.getNotif_action()%></td>
					<td><%=cx.getNotif_item_number()%></td>
					<td><%=cx.getNotif_docdate()%></td>
					<td><%=cx.getVehicle_id_7()%></td>
					<td><%=cx.getMf_order_no()%></td>
					<td><%=cx.getVehicle_state()%></td>
					<td><%=cx.getClient()%></td>
					<td><%=cx.getProduct_type()%></td>
					<td><%=cx.getBrand()%></td>
					<td><%=cx.getMf_model_code()%></td>
					<td><%=cx.getWs_model_code()%></td>
					<td><%=cx.getOrder_type()%></td>
					<td><%=cx.getIvs_order_status()%></td>
					<td><%=cx.getProcessing_type()%></td>
					<td><%=cx.getWs_business_type()%></td>
					<td><%=cx.getNv_mf_orderer_ca()%></td>
					<td><%=cx.getNv_mf_orderer_id()%></td>
					<td><%=cx.getOrderer_domestic()%></td>
					<td><%=cx.getInvoicing_dealer()%></td>
					<td><%=cx.getMod_function()%></td>
					<td><%=cx.getMod_system()%></td>
					<td><%=cx.getMod_user()%></td>
					<td><%=cx.getVersion()%></td>
					<td><%=cx.getPartition_key()%></td>
					<td><%=cx.getDealer_class()%></td>
					<%
						}
					%>
				</tr>
			</tbody>
		</table>
	</div>

</body>
<style>
body {
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	background-color: lightblue;
}
</style>
</html>