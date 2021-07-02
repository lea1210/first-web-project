import { Foto } from "./Foto";
import {reactive, readonly} from 'vue';
import { Client, Message } from "@stomp/stompjs";
import { FotoMessage } from "./FotoMessage";
import { useLoginStore } from '@/services/LoginStore';

const wsurl = "ws://localhost:9090/messagebroker"
const DEST = "/topic/foto"

const fotostate = reactive({
    fotos: Array<Foto>(),
    errormessage: ''
})

export function useFotoStore(){
    return{
        fotostate: readonly(fotostate),
        updateFotos,
        deleteFoto
    }

}

function updateFotos(){
    const {loginstate, doLogin,doLogout} = useLoginStore();
    console.log("jwtoken: " + loginstate.jwttoken);

    fetch('api/foto',{
        headers: {'Authorization': 'Bearer'+loginstate.jwttoken}
    })
    .then((response) =>{
        if(!response.ok){
            console.log("Response nicht okay");
            fotostate.errormessage = response.statusText
        }else{
            fotostate.errormessage = ''
            return response.json()
        }
    })
    .then((jsondata)=>{
        console.log("Fotos erhalten")
        fotostate.fotos = jsondata
    })
    .catch(reason => {
        fotostate.errormessage = reason
    });
}

function deleteFoto(id:number){
    const {loginstate, doLogin,doLogout} = useLoginStore();

    fetch('api/foto/'+id,{
        method: 'DELETE',
        headers: {'Authorization': 'Bearer' +loginstate.jwttoken}
    })
    .then((response) =>{
        if(!response.ok){
            console.log("Response nicht okay");
            fotostate.errormessage = response.statusText
        }else{
            console.log("Foto geloescht")
            fotostate.errormessage = ''
        }
    })
    .catch(reason => {
        fotostate.errormessage = reason
    });
}

const stompclient = new Client({brokerURL : wsurl})
stompclient.onWebSocketError = (event) =>{
    //Webservice Fehler
    console.log("Webservice-Fehler")
}
stompclient.onStompError = (frame) =>{
    //STOMP-Fehler
    console.log("STOMP-Fehler")
}
stompclient.onConnect = (frame) =>{
    console.log("stompclient connected")

    stompclient.subscribe(DEST, (message) => {
        console.log("Callback -> Nachricht auf DEST empfangen")
        const jsonMessage = JSON.parse(message.body)
        if(jsonMessage.operation == "fotoGespeichert" || jsonMessage.operation == "fotoGeloescht"){
        console.log("Foto gelöscht oder gespeichert")
        updateFotos()
        }
    });

}
stompclient.onDisconnect = () => {
    console.log("Verbindungsabbau")
}
//Verbindung zum Broker aufbauen
stompclient.activate();

