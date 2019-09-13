<!DOCTYPE html>
<html>
<head>
<title>Create Animal</title>
</head>
<body>
	<div style="padding-left:100px;font-family: monospace;">
		<h2>Create Animal</h2>
		<form action="rest/persons" method="POST">
			<div style="width: 200px; text-align: left;">
				<div style="padding:10px;">
					Person ID: <input name="id" />
				</div>
				<div style="padding:10px;">
					Person Name: <input name="pname" />
				</div>
				<div style="padding:10px;text-align:center">
					<input type="submit" value="Submit" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>