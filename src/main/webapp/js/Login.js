$(document).ready(function () {
    if(window.localStorage.getItem('loginUser') != null){
        redirect('landing.html');
    }
});

const redirect = (page) => {
    window.location.href = page;
}

const validate = () => {
    let user = $('#usuario').val();
    let password = $('#password').val();

    user = user.trim();
    if(usuario === '' || password === ''){
        showError('Diligencia todos los campos.');
        return false;
    }
        
    if(!usuarios.some( e => e.usuario == user && e.password == password) ){
        showError('El usuario y la contraseña no coinciden.');
        return false;
    }else{
        let userLogin = usuarios.find(element => element.usuario == user && element.password == password)
        let loginUser = {
            usuario : userLogin.usuario,
            typeUser : userLogin.typeUser,
            idUsuario : userLogin.idUsuario
        }
        window.localStorage.setItem('loginUser',JSON.stringify(loginUser));
        window.localStorage.setItem('pqrsArray',JSON.stringify(pqrsArray));
        redirect("landing.html");
    }
}
//^[a-zA-Z]+([_\s\-]?[a-zA-Z0-9])*$

const showError = (message) => {
    $('.toast-body').html(message);
    $('.toast').toast('show');
}