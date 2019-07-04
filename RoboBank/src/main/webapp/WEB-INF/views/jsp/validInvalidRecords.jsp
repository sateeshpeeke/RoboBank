<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style>
#customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#myTable td, #myTable th {
	border: 1px solid #ddd;
	padding: 8px;
}

#myTable tr:nth-child(even) {
	background-color: #f2f2f2;
}

#myTable tr:hover {
	background-color: #ddd;
}

#myTable th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: white;
}
</style>
</head>
<title>XML/CSV file uploading</title>
<body>
	<div align="center">

		<form name="fileUpload" method="POST" action="processStatment"
			enctype="multipart/form-data">
			<fieldset>
				<legend style="color: blue">
					<h1>Rabobank Customer Statement Processor</h1>
				</legend>
				<label>Select File</label> <input type="file" name="file" /> <input
					type="submit" name="submit" value="Upload" />
			</fieldset>
		</form>
	</div>
	<div style="width: 50%; float: left">

		<h1>Valid Records</h1>
		<table id="myTable">
			<thead>
				<tr>
					<th>Transaction Reference</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="validRecords" items="${validRecords}"
					varStatus="status">

					<tr>
						<td>${validRecords.transactionRef}</td>
						<td>${validRecords.description}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<div style="width: 50%; float: left">

		<h1>Invalid Records</h1>
		<table id="myTable">
			<thead>
				<tr>
					<th>Transaction Reference</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="inValidRecords" items="${inValidRecords}"
					varStatus="status">

					<tr>
						<td>${inValidRecords.transactionRef}</td>
						<td>${inValidRecords.description}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>