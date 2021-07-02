import {reactive, readonly} from 'vue';


const loginstate = reactive({
    username: '',
    jwttoken: '',
    errormessage: '',
    isLoggedIn: false
})


export function useLoginStore(){
    return{
        loginstate : readonly(loginstate),
        doLogin,
        doLogout
    }

}

function doLogout(){
    loginstate.username =  '';
    loginstate.jwttoken =  '';
    loginstate.errormessage = '';
    loginstate.isLoggedIn= false

}


async function doLogin(username: string, password:string): Promise<boolean>{
    console.log("Im doLogin angekommen");
    console.log("username: " + username);
    console.log("password: " + password);
    const credentials = btoa(`${username}:${password}`);
    const userobj = {'username': username, 'password': password};
    const answer = await fetch('api/login' ,{
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(userobj),
    })
   
    if(!answer.ok){
        console.log("Login fehlgeschlagen, ich logge aus")
        doLogout();
        loginstate.errormessage = answer.statusText
        return false;

    }else{
        const response = await answer.text();
        console.log("Token ist zurueckgekommen");
        loginstate.username = username;
        loginstate.jwttoken = response;
        loginstate.isLoggedIn = true;
        loginstate.errormessage = '';
        return true;
    }

}