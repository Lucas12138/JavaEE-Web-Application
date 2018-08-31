function calculate(operation) {
	
	switch(operation) {
		case '+':
			document.getElementById("ans").value = parseInt(document.getElementById("x").value) + parseInt(document.getElementById("y").value);
			break;
		case '-':
			document.getElementById("ans").value = parseInt(document.getElementById("x").value) - parseInt(document.getElementById("y").value);
			break;
		case '*':
			document.getElementById("ans").value = parseInt(document.getElementById("x").value) * parseInt(document.getElementById("y").value);
			break;
		case '/':
			document.getElementById("ans").value = parseInt(document.getElementById("x").value) / parseInt(document.getElementById("y").value);
			break;
		default:
			return;
	}
}