<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>XML/CSV file uploading</title>
<body>
	<div align="center">
	
		<form name="fileUpload" method="POST" action="processStatment"
			enctype="multipart/form-data">
			<fieldset>
			<legend style="color:blue"><h1> Rabobank Customer Statement Processor</h1></legend>
			<label>Select File</label>  <input type="file" name="file" />
			<input type="submit" name="submit" value="Upload" />
			</fieldset>
		</form>
	</div>


</body>
</html>