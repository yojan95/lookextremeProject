function validar(form){
	event.preventDefault();

	var name = form.nombre;
	var nameExpre = /[a-z]/;
	if (name.value == "" || name.value == "nombre de usuario" ) {
		alert("ingrese su nombre");
		name.focus();
		name.select();
		return false;
	}else if (!nameExpre.test(name.value)) {
		alert("El nombre no es valido, Ejemplo: juan");
		name.focus();
		return false;
	}

	var name2 = form.apellido;
	var name2Expre = /[a-z]/;
	if (name2.value == null || name2.value == "") {
		alert("ingrese su apellido");
		name2.focus();
		name2.select();
		return false;
	}else if (!name2Expre.test(name2.value)) {
		alert("El apellido no es valido, Ejemplo: Lopez");
		name2.focus();
		return false;
	}

	var fecha = form.bday;
	if (fecha.value == null || fecha.value == "") {
		alert("Por favor seleccione una fecha");
		fecha.focus();
		return false;
	}

	var email = form.correo1;
	var emailExpre = /\w+@\w+\.+[a-z]/;
	if (email.value == null || email.value == "") {
		alert("ingrese un correo");
		email.focus();
		email.select();
		return false;
	}else if (!emailExpre.test(email.value)) {
		alert("El correo no es valido, Ejemplo: juan@gmail.com");
		email.focus();
		return false;
	}

	var pass = form.contraseña;
	if (pass.value == null || pass.value == "") {
		alert("Ingrese una contraseña");
		pass.focus();
		pass.select();
		return false;
	}

	var telef = form.telefono1;
	var telefExpre = /^(?:\+|-)?\d+$/;
	if (telef.value == null || telef.value =="") {
		alert("Ingrese su telefono");
		telef.focus();
		telef.select();
		return false;
	}else if (!telefExpre.test(telef.value)) {
		alert("El Telefono no es valido, Ejemplo: 342899");
		telef.focus();
		return false;
	}


	var cedu = form.cedula1;
	var ceduExpre = /^(?:\+|-)?\d+$/;
	if (cedu.value == null || cedu.value =="") {
		alert("Ingrese su cedula");
		cedu.focus();
		cedu.select();
		return false;
	}else if (!ceduExpre.test(cedu.value)) {
		alert("La cedula no es valida, Ejemplo: 1087897654");
		cedu.focus();
		return false;
	}

	var edad = form.edad1;
	var edadExpre = /^(?:\+|-)?\d+$/;
	if (edad.value == null || edad.value =="") {
		alert("Ingrese su edad");
		edad.focus();
		edad.select();
		return false;
	}else if (!edadExpre.test(edad.value)) {
		alert("La edad no es valida, Ejemplo: 25");
		edad.focus();
		return false;
	}

	var ciudad = form.ciudad1;
	var ciudadExpre = /[a-z]/;
	if (ciudad.value == null || ciudad.value =="") {
		alert("Ingrese su ciudad");
		ciudad.focus();
		ciudad.select();
		return false;
	}else if (!ciudadExpre.test(ciudad.value)) {
		alert("La ciudad no es valida Ejemplo: Bogota");
		ciudad.focus();
		return false;
	}
	var direccion = form.direccion1;
	if (direccion.value == null || direccion.value =="") {
		alert("Ingrese su direccion");
		direccion.focus();
		direccion.select();
		return false;
	}
	alert("Se han guardado todos los cambios ");
	form.submit();


}
/*
function solonumeros(e)
                    {
         var key = window.event ? e.which : e.keyCode;
                        if(key < 48 || key > 57)
                            e.preventDefault();
                    }

*/