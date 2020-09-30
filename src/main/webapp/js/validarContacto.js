function validar(form){
	event.preventDefault();

	var name = form.nombre;
	var nameExpre = /[a-z]/;
	if (name.value == null || name.value == "") {
		alert("Por favor ingrese su nombre");
		name.focus();
		name.select();
		return false;
	}else if (!nameExpre.test(name.value)){
		alert("El nombre no es valido");
		name.focus();
		return false;
	}

	var correo = form.correoo;
	var correoExpre = /\w+@\w+\.+[a-z]/;
	if (correo.value == null || correo.value == "") {
		alert("Por favor ingrese su correo");
		correo.focus();
		correo.select();
		return false;
	}else if (!correoExpre.test(correo.value)) {
		alert("El correo no es valido, ejemplo: juan@gmail.com");
		correo.focus();
		return false;
	}

	var mensaje = form.mensaje;
	if (mensaje.value == null || mensaje.value == "") {
		alert("Por favor ingrese su mensaje");
		mensaje.focus();
		mensaje.select();
		return false;
	}
	alert("Su mensaje ha sido enviado");
	form.submit();
}