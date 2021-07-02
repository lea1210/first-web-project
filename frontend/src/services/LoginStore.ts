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
    fetch('api/login' ,{
        method: 'POST',
        headers: {'Authorization': 'Basic ' +  credentials,'Content-Type': 'application/json'},
        body: JSON.stringify(userobj),
    })
    .then((response) =>{
        if(!response.ok){
            console.log("response nicht okay");
            loginstate.errormessage = response.statusText
        }else{
            
            loginstate.errormessage = ''
            console.log("response: " + response);
            return response.text();
        }
    })
    .then((jsondata)=>{
        if(jsondata != undefined){
            console.log("jsondata: " + jsondata);
            loginstate.jwttoken = jsondata;
            loginstate.isLoggedIn = true;
            loginstate.username = username;
            loginstate.errormessage = '';
        }else{
            console.log("undeifined kam zurueck, ich logge aus")
            doLogout();
        }      

    })
    .catch(reason => {
        loginstate.errormessage = reason
    });

    return false;


}