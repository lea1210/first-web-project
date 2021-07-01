import {reactive, readonly} from 'vue';


const loginstate = reactive({
    username: '',
    jwttoken: '',
    errormessage: '',
    isLoggedIn: false
})

function doLogout(){
    loginstate.username =  '';
    loginstate.jwttoken =  '';
    loginstate.errormessage = '';
    loginstate.isLoggedIn= false

}

async function doLogin(username: string, password:string): Promise<boolean>{
    fetch('api/login' ,{
        method: 'POST'
    })
    .then((response) =>{
        if(!response.ok){
            loginstate.errormessage = response.statusText
        }else{
            loginstate.errormessage = ''
            
        }
    })
    .catch(reason => {
        loginstate.errormessage = reason
    });

    return false;


}